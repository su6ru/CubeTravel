package com.cube.cubetravel.data.repository

import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.beans.ImagesBean
import com.cube.cubetravel.data.network.ITravelApiService
import com.cube.cubetravel.data.network.call.TravelApiCall
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import com.cube.cubetravel.sql.dao.AttractionsCollectionImageUrlDao
import com.cube.cubetravel.sql.table.AttractionsCollectionImageUrlTable
import com.cube.cubetravel.sql.table.AttractionsCollectionTable
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.first
import retrofit2.http.Path
import retrofit2.http.Query

/** MainActivity相關的  Repository*/
class MainRepository(private val attractionsCollectionDao: AttractionsCollectionDao
,private val attractionsCollectionImageUrlDao: AttractionsCollectionImageUrlDao) {
    /** 景點列表
     * @param language 語系
     * @param categoryIds 類別ID
     * @param nlat 緯度(可能因後台資料本身不多,所以實測後我認為是多餘的)
     * @param elong 經度(可能因後台資料本身不多,所以實測後我認為是多餘的)
     * @param page 當前請求的頁碼,目前30筆資料1頁。
     */
    fun callGetAttractionsList(language: String?
                        , categoryIds: String?
                        , nlat: String?
                        , elong: String?
                        , page: String?
                        , successListener: IOnOptionListener<ApiBase.GetAttractionsList.Response>
                        , failListener: IOnOptionListener<String>
                        , completeListener: IOnOptionListener<Void>){

        ITravelApiService
            .invoke()
            .getAttractionsList(language,categoryIds,nlat,elong,page)
            .enqueue(TravelApiCall(successListener,failListener,completeListener))
    }
    /** 最新消息列表
     * @param language 語系
     * @param begin 開始時間，格式 yyyy-MM-dd
     * @param end 結束時間，格式 yyyy-MM-dd
     * @param page 當前請求的頁碼,目前30筆資料1頁。
     */
    fun callGetNewsList(language: String?
                        , begin: String?
                        , end: String?
                        , page: String?
                        , successListener: IOnOptionListener<ApiBase.GetNewsList.Response>
                        , failListener: IOnOptionListener<String>
                        , completeListener: IOnOptionListener<Void>){

        ITravelApiService
            .invoke()
            .getNewsList(language,begin,end,page)
            .enqueue(TravelApiCall(successListener,failListener,completeListener))
    }
    /** 取的當前語系 的 value  */
    fun getSelectedLanguageValue(): String{
        val languageBean = CubeTravelApplication.INSTANCE.mAppManager.mLanguageBean
        return languageBean.value
    }

    /** 檢查db 此景點資料是否已收藏 */
    fun isAttractionsCollectionDataExist(id: Int): Boolean{

        val attractionsCollectionTable = attractionsCollectionDao.getTableById(id)

        return attractionsCollectionTable != null
    }
    /** 檢查db 此景點資料 圖片 是否已收藏 */
    fun isAttractionsCollectionImageUrlDataExist(attractionsItemId: Int,imagesBean: ImagesBean): Boolean{
        //src
        val src = imagesBean.src
        //subject
        val subject = imagesBean.subject
        //ext
        val ext = imagesBean.ext

        val attractionsCollectionTable = attractionsCollectionImageUrlDao.getTableByAllParam(attractionsItemId,src,subject,ext)

        return attractionsCollectionTable != null
    }
    /** 新增 景點收藏 */
    suspend fun insertAttractionsCollection(attractionsBean: AttractionsBean){

        val attractionsCollectionTable = AttractionsCollectionTable(
            attractions_item_id = attractionsBean.id
            , name = attractionsBean.name
            , introduction = attractionsBean.introduction
            , openTime = attractionsBean.openTime
            , address = attractionsBean.address
            , url = attractionsBean.url)

        attractionsCollectionDao.insert(attractionsCollectionTable)
    }
    /** 新增 景點收藏 的 圖片資料 */
    suspend fun insertAttractionsCollectionImageUrl(attractionsBean: AttractionsBean){
        val imagesBeanList = attractionsBean.imagesBeanList
        //如果此資料沒有 id 則不存入
        val attractionsItemId = attractionsBean.id ?: return
        //如果此資料沒有圖片則不存入
        if (imagesBeanList.isNullOrEmpty()){
            return
        }
        val attractionsCollectionImageUrlTableList = mutableListOf<AttractionsCollectionImageUrlTable>()
        for (imagesBean in imagesBeanList){
            //若db已有此資料則跳過
            if (isAttractionsCollectionImageUrlDataExist(attractionsItemId,imagesBean)){
                continue
            }
            val attractionsCollectionImageUrlTable = AttractionsCollectionImageUrlTable(
                attractions_item_id = attractionsItemId
                , src = imagesBean.src
                , subject = imagesBean.subject
                , ext = imagesBean.ext)
            attractionsCollectionImageUrlTableList.add(attractionsCollectionImageUrlTable)
        }

        attractionsCollectionImageUrlDao.insertTableList(attractionsCollectionImageUrlTableList)
    }
    /** 取得 全部已存的 收藏資料(含圖片) 並轉成 AttractionsCollectionBean輸出  */
    suspend fun getDbAttractionsCollectionList(): List<AttractionsCollectionBean>{
        // 取 全部已存的 收藏資料
        val allAttractionsCollectionTableList = attractionsCollectionDao.getAllTableList().first()

        val attractionsBeanList = mutableListOf<AttractionsCollectionBean>()

        for (attractionsCollectionTable in allAttractionsCollectionTableList){
            val tableAttractionsItemId = attractionsCollectionTable.attractions_item_id
            //取得此資料 的圖片資料array
            val mergeImagesBeanList = mutableListOf<ImagesBean>()
            val imageUrlTableList = attractionsCollectionImageUrlDao.getTableListByAttractionsItemId(tableAttractionsItemId!!).first()
            //組合此景點的圖片資料
            if (imageUrlTableList.isNotEmpty()){
                for (imageUrlTable in imageUrlTableList){
                    val imagesBean = ImagesBean().apply {
                        this.src = imageUrlTable.src
                        this.subject = imageUrlTable.subject
                        this.ext = imageUrlTable.ext
                    }
                    mergeImagesBeanList.add(imagesBean)
                }
            }
            //組合景點收藏資料
            val attractionsCollectionBean = AttractionsCollectionBean().apply {
                this.id = attractionsCollectionTable.id
                this.attractionsItemId = attractionsCollectionTable.attractions_item_id
                this.name = attractionsCollectionTable.name
                this.introduction = attractionsCollectionTable.introduction
                this.openTime = attractionsCollectionTable.openTime
                this.address = attractionsCollectionTable.address
                this.url = attractionsCollectionTable.url
                this.imagesBeanList = mergeImagesBeanList
                this.isCollection = true
            }
            attractionsBeanList.add(attractionsCollectionBean)
        }
        return attractionsBeanList
    }
    /** 刪除 景點收藏 */
    suspend fun deleteAttractionsCollection(attractionsItemId: Int){

        val attractionsCollectionTable = attractionsCollectionDao.getTableById(attractionsItemId) ?: return

        attractionsCollectionDao.deleteTable(attractionsCollectionTable)
    }
    /** 刪除 景點收藏 圖片 */
    suspend fun deleteAttractionsCollectionImageUrl(attractionsItemId: Int){

         attractionsCollectionImageUrlDao.deleteDataByAttractionsItemId(attractionsItemId)
    }
}