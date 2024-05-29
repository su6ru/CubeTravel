package com.cube.cubetravel.feature.main.viewmodel

import android.content.res.Resources
import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.MainRepository

/** MainActivity相關的  ViewModel*/
class MainViewModel(private val mainRepository: MainRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //呼叫 取得 景點
        callGetAttractionsList(CubeTravelConfig.PAGE_DEFAULT)
        //呼叫 取得 最新消息
        callGetNewsList(CubeTravelConfig.PAGE_DEFAULT)

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

    /** 觸發點擊 景點 */
    val mAttractionsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 最新消息 */
    val mNewsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 景點收藏 */
    val mAttractionsCollectionClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 設定 */
    val mSettingClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 語言 */
    val mLanguageClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 前往官網 */
    val mGoToWebClickLiveData = MutableLiveData<WebBean>()

    /** 景點列表 資料的 LiveData */
    val mAttractionsBeanListLiveData = MutableLiveData<List<AttractionsBean>>()
    /** 最新消息 資料的 LiveData */
    val mNewsBeanListLiveData = MutableLiveData<List<NewsBean>>()



    /** 景點列表itemView 點擊事件 的LiveData*/
    val mAttractionsListItemClickLiveData = MutableLiveData<AttractionsBean>()
    /** 最新消息列表itemView 點擊事件 的LiveData*/
    val mNewsListItemClickLiveData = MutableLiveData<NewsBean>()


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
    /** 當 景點收藏 Recyclerview 滑動到最頂部,觸發重新整理效果 */
    val mOnAttractionsCollectionListRefreshListener = object: IOnOptionListener<Void>{
        override fun onExecute(option: Void?) {
        }

    }

    /** 當點擊 景點列表 的itemView*/
    fun onAttractionsListItemClick(attractionsBean : AttractionsBean){
        mAttractionsListItemClickLiveData.value = attractionsBean
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
    fun onAttractionsListCheckedChangeListener(attractionsBean: AttractionsBean,isChecked: Boolean){

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

                    //從API取得的資料List
                    var apiAttractionsBeanList = option?.data
                    if (apiAttractionsBeanList.isNullOrEmpty()){
                        apiAttractionsBeanList = mutableListOf()
                    }
                    willUseAttractionsBeanList.addAll(apiAttractionsBeanList)

                    mAttractionsBeanListLiveData.value = willUseAttractionsBeanList
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