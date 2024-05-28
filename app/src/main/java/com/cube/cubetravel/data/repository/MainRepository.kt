package com.cube.cubetravel.data.repository

import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.network.ITravelApiService
import com.cube.cubetravel.data.network.call.TravelApiCall
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import retrofit2.http.Path
import retrofit2.http.Query

/** MainActivity相關的  Repository*/
class MainRepository(private val attractionsCollectionDao: AttractionsCollectionDao) {
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
}