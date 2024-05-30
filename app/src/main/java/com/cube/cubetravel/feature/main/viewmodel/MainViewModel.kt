package com.cube.cubetravel.feature.main.viewmodel

import android.content.res.Resources
import android.util.Log
import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** MainActivity相關的  ViewModel*/
class MainViewModel(private val mainRepository: MainRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //呼叫 取得 景點
        callGetAttractionsList(CubeTravelConfig.PAGE_DEFAULT)
        //呼叫 取得 最新消息
        callGetNewsList(CubeTravelConfig.PAGE_DEFAULT)
        //取得收藏
        loadAttractionsCollection()
    }

    // MARK:- ========================== Data
    /** 景點資料 總頁數  */
    var mAttractionsListTotalPage = "1"
    /** 景點資料 當前頁碼  */
    var mAttractionsListNowPage = "1"
    /** 最新消息資料 總頁數  */
    var mNewsListTotalPage = "1"
    /** 最新消息資料 當前頁碼  */
    var mNewsListNowPage = "1"

    /** 觸發點擊 Tab之景點 */
    val mAttractionsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 Tab之最新消息 */
    val mNewsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 Tab之景點收藏 */
    val mAttractionsCollectionClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 Tab之設定 */
    val mSettingClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 語言 */
    val mLanguageClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 前往官網 */
    val mGoToWebClickLiveData = MutableLiveData<WebBean>()

    /** 景點列表 資料的 LiveData */
    val mAttractionsBeanListLiveData = MutableLiveData<List<AttractionsBean>>()
    /** 最新消息列表 資料的 LiveData */
    val mNewsBeanListLiveData = MutableLiveData<List<NewsBean>>()
    /** 景點收藏列表 資料的 LiveData */
    val mAttractionsCollectionBeanListLiveData = MutableLiveData<List<AttractionsCollectionBean>>()


    /** 景點列表itemView 點擊事件 的LiveData*/
    val mAttractionsListItemClickLiveData = MutableLiveData<AttractionsBean>()
    /** 最新消息列表itemView 點擊事件 的LiveData*/
    val mNewsListItemClickLiveData = MutableLiveData<NewsBean>()
    /** 收藏景點列表itemView 點擊事件 的LiveData*/
    val mAttractionsCollectionListItemClickLiveData = MutableLiveData<AttractionsCollectionBean>()

    // MARK:- ========================== Event
    /** 當點擊 tab radiobutton */
    val mOnCheckedChangeListener = object : CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

            val id = buttonView?.id
            //景點
            if (id == R.id.radiobutton_attractions){
                if (isChecked) {
                    mAttractionsClickLiveData.value = true
                }
                return
            }
            //最新消息
            if (id == R.id.radiobutton_news){
                if (isChecked) {
                    mNewsClickLiveData.value = true
                }
                return
            }
            //景點收藏
            if (id == R.id.radiobutton_attractions_collection){
                if (isChecked) {
                    mAttractionsCollectionClickLiveData.value = true
                }
                return
            }
            //設定
            if (id == R.id.radiobutton_setting){
                if (isChecked) {
                    mSettingClickLiveData.value = true
                }
                return
            }
        }
    }
    /** 當 景點 Recyclerview 滑動到最頂部,觸發重新整理效果 */
    val mOnAttractionsListRefreshListener = object: IOnOptionListener<Void>{
        override fun onExecute(option: Void?) {
            resetAttractionsData()

            callGetAttractionsList(CubeTravelConfig.PAGE_DEFAULT)
        }

    }
    /** 當 最新消息 Recyclerview 滑動到最頂部,觸發重新整理效果 */
    val mOnNewsListRefreshListener = object: IOnOptionListener<Void>{
        override fun onExecute(option: Void?) {
            resetNewsData()
            callGetNewsList(CubeTravelConfig.PAGE_DEFAULT)

        }

    }


    /** 當點擊 景點列表 的itemView*/
    fun onAttractionsListItemClick(attractionsBean : AttractionsBean){
        mAttractionsListItemClickLiveData.value = attractionsBean
    }
    /** 當點擊 收藏景點列表 的itemView*/
    fun onAttractionsCollectionListItemClick(attractionsCollectionBean : AttractionsCollectionBean){
        mAttractionsCollectionListItemClickLiveData.value = attractionsCollectionBean
    }
    /** 當點擊 設定內的 語言 */
    fun onLanguageClickClick(){
        mLanguageClickLiveData.value = true
    }
    /** 當點擊 設定內的 前往官網 */
    fun onGoToWebClickClick(){

        val webBean = WebBean()
        webBean.url = CubeTravelConfig.WEB_URL

        mGoToWebClickLiveData.value = webBean
    }

    /** 當點擊 景點列表的 收藏 */
    fun onAttractionsListFavoriteCheckedChangeListener(attractionsBean: AttractionsBean,isChecked: Boolean){
        val id = attractionsBean.id
        if (id == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
            return
        }

        if (isChecked){
            attractionsBean.isCollection = true

            viewModelScope.launch(Dispatchers.IO) {
                //檢查是否已存在,若有就不再存
                val isNewsCollectionDataExist = mainRepository.isAttractionsCollectionDataExist(id)
                if (!isNewsCollectionDataExist){
                    mainRepository.insertAttractionsCollection(attractionsBean)
                    mainRepository.insertAttractionsCollectionImageUrl(attractionsBean)

                    loadAttractionsCollection()
                }
            }

        }else{
            viewModelScope.launch(Dispatchers.IO) {
                attractionsBean.isCollection = false

                mainRepository.deleteAttractionsCollection(id)
                mainRepository.deleteAttractionsCollectionImageUrl(id)
                loadAttractionsCollection()
            }
        }
    }

    /** 當點擊 收藏景點列表的 收藏 */
    fun onAttractionsCollectionListFavoriteCheckedChangeListener(attractionsCollectionBean: AttractionsCollectionBean,isChecked: Boolean){

        if (!isChecked) {
            val attractionsItemId = attractionsCollectionBean.attractionsItemId
            if (attractionsItemId == null) {
                mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
                return
            }

            viewModelScope.launch(Dispatchers.IO) {
                //刪除 景點收藏資料
                mainRepository.deleteAttractionsCollection(attractionsItemId)
                //刪除 景點收藏圖片資料
                mainRepository.deleteAttractionsCollectionImageUrl(attractionsItemId)
                //更新 景點收藏列表
                loadAttractionsCollection()


                //  更新 景點列表,這也可以,但這是大範圍更新,比較耗效能
                val uiAttractionsBeanList = mAttractionsBeanListLiveData.value
                val newAttractionsBeanList = mutableListOf<AttractionsBean>()
                if (uiAttractionsBeanList != null) {
                    for (uiAttractionsBean in uiAttractionsBeanList) {
                        val attractionsBean = AttractionsBean().copy(uiAttractionsBean)
                        if (attractionsBean.id == attractionsItemId) {
                            attractionsBean.isCollection = isChecked
                        }
                        else{
                            attractionsBean.isCollection = uiAttractionsBean.isCollection
                        }
                        newAttractionsBeanList.add(attractionsBean)
                    }
                    viewModelScope.launch(Dispatchers.Main) {
                        mAttractionsBeanListLiveData.value = newAttractionsBeanList
                    }

                }
            }
        }
    }
    /** 當點擊 最新消息列表 的itemView*/
    fun onNewsListItemClick(newsBean : NewsBean){
        mNewsListItemClickLiveData.value = newsBean
    }

    /** 當 景點列表 滑到最底部 */
    val mAttractionsListBottomReachedListener = object : IOnOptionListener<Void>{
        override fun onExecute(option: Void?) {

            //計算下一頁頁碼
            if (mAttractionsListTotalPage.isNullOrEmpty()) {
                return
            }
            val nextPage: Int = mAttractionsListNowPage.toInt() + 1
            if (nextPage > mAttractionsListTotalPage.toInt()) {
                return
            }
            callGetAttractionsList(nextPage.toString())
        }

    }
    /** 當 最新消息列表 滑到最底部 */
    val mNewsListBottomReachedListener = object : IOnOptionListener<Void>{
        override fun onExecute(option: Void?) {

            //計算下一頁頁碼
            if (mNewsListTotalPage.isNullOrEmpty()) {
                return
            }
            val nextPage: Int = mNewsListNowPage.toInt() + 1
            if (nextPage > mNewsListTotalPage.toInt()) {
                return
            }
            callGetNewsList(nextPage.toString())
        }

    }
    // MARK:- ========================== Method

    /** 呼叫 取得 景點列表 */
    fun callGetAttractionsList(page: String){
        mIsLoadingLiveData.value = true

        mainRepository.callGetAttractionsList(mainRepository.getSelectedLanguageValue()
            ,null
            ,null
            ,null
            ,page
            ,object: IOnOptionListener<ApiBase.GetAttractionsList.Response>{
                override fun onExecute(option: ApiBase.GetAttractionsList.Response?) {
                    //總頁數計算
                    val totalCount = option?.total
                    if (totalCount != null) {
                        var totalPage = totalCount / CubeTravelConfig.COUNT_EVERY_PAGE_DATA
                        if (totalCount % CubeTravelConfig.COUNT_EVERY_PAGE_DATA != 0){
                            totalPage ++
                        }
                        mAttractionsListTotalPage = totalPage.toString()
                    }
                    //刷新 當前資料 頁碼紀錄
                    mAttractionsListNowPage = page

                    //預計套到recyclerview的資料List
                    var willUseAttractionsBeanList: MutableList<AttractionsBean> = mutableListOf()

                    //當前UI中的資料List
                    if (page != CubeTravelConfig.PAGE_DEFAULT){
                        var nowUIAttractionsBeanList = mAttractionsBeanListLiveData.value
                        if (nowUIAttractionsBeanList == null){
                            nowUIAttractionsBeanList = mutableListOf()
                        }
                        willUseAttractionsBeanList.addAll(nowUIAttractionsBeanList)
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        //從API取得的資料List
                        var apiAttractionsBeanList = option?.data
                        if (apiAttractionsBeanList.isNullOrEmpty()){
                            apiAttractionsBeanList = mutableListOf()
                        }else {
                            for (apiAttractionsBean in apiAttractionsBeanList){
                                val id = apiAttractionsBean.id
                                if (id != null) {

                                    if (mainRepository.isAttractionsCollectionDataExist(id)){
                                        apiAttractionsBean.isCollection = true
                                    }
                                }
                            }
                        }
                        willUseAttractionsBeanList.addAll(apiAttractionsBeanList)
                        withContext(Dispatchers.Main) {
                            mAttractionsBeanListLiveData.value = willUseAttractionsBeanList

                        }
                    }
                }

            },object: IOnOptionListener<String>{
                override fun onExecute(option: String?) {
                    mMsgLiveData.value = option
                }

            },object : IOnOptionListener<Void>{
                override fun onExecute(option: Void?) {
                    mIsLoadingLiveData.value = false
                }

            })
    }
    /** 呼叫 取得 最新消息 列表 */
    fun callGetNewsList(page: String){

        mIsLoadingLiveData.value = true

        mainRepository.callGetNewsList(mainRepository.getSelectedLanguageValue()
            ,null
            ,null
            ,page
            ,object: IOnOptionListener<ApiBase.GetNewsList.Response>{
                override fun onExecute(option: ApiBase.GetNewsList.Response?) {
                    //總頁數計算
                    val totalCount = option?.total
                    if (totalCount != null) {
                        var totalPage = totalCount / CubeTravelConfig.COUNT_EVERY_PAGE_DATA
                        if (totalCount % CubeTravelConfig.COUNT_EVERY_PAGE_DATA != 0){
                            totalPage ++
                        }
                        mNewsListTotalPage = totalPage.toString()
                    }
                    //刷新 當前資料 頁碼紀錄
                    mNewsListNowPage = page

                    //預計套到recyclerview的資料List
                    var willUseNewsBeanList: MutableList<NewsBean> = mutableListOf()
                    //當前UI中的資料List
                    if (page != CubeTravelConfig.PAGE_DEFAULT){
                        var nowUINewsBeanList = mNewsBeanListLiveData.value
                        if (nowUINewsBeanList == null){
                            nowUINewsBeanList = mutableListOf()
                        }
                        willUseNewsBeanList.addAll(nowUINewsBeanList)
                    }

                    //從API取得的資料List
                    var apiNewsBeanList = option?.data
                    if (apiNewsBeanList.isNullOrEmpty()){
                        apiNewsBeanList = mutableListOf()
                    }
                    willUseNewsBeanList.addAll(apiNewsBeanList)

                    mNewsBeanListLiveData.value = willUseNewsBeanList
                }

            },object: IOnOptionListener<String>{
                override fun onExecute(option: String?) {
                    mMsgLiveData.value = option
                }

            },object : IOnOptionListener<Void>{
                override fun onExecute(option: Void?) {
                    mIsLoadingLiveData.value = false
                }

            })
    }
    /** 讀取 景點收藏資料 (含圖片) */
    fun loadAttractionsCollection(){
        viewModelScope.launch(Dispatchers.IO) {

            val mAttractionsCollectionBeanList = mainRepository.getDbAttractionsCollectionList()

            withContext(Dispatchers.Main){
                mAttractionsCollectionBeanListLiveData.value = mAttractionsCollectionBeanList
            }
        }
    }
    /** 重置 景點 列表資料 */
    fun resetAttractionsData(){
        mAttractionsListTotalPage = "1"

        mAttractionsListNowPage = "1"

    }
    /** 重置 最新消息 列表資料 */
    fun resetNewsData(){
        mNewsListTotalPage = "1"

        mNewsListNowPage = "1"

    }
    /** 重置 全部資料 資料 */
    fun reloadAllListData(){
        resetAttractionsData()
        callGetAttractionsList(CubeTravelConfig.PAGE_DEFAULT)

        resetNewsData()
        callGetNewsList(CubeTravelConfig.PAGE_DEFAULT)
    }

}