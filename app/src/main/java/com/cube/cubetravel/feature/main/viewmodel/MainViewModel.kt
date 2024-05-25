package com.cube.cubetravel.feature.main.viewmodel

import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.MainRepository

/** MainActivity相關的  ViewModel*/
class MainViewModel(private val mainRepository: MainRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        callGetAttractionsList(CubeTravelConfig.PAGE_DEFAULT)

    }

    // MARK:- ========================== Data
    /** 景點資料 總頁數  */
    var mAttractionsListTotalPage = "1"

    /** 景點資料 當前頁碼  */
    var mAttractionsListNowPage = "1"

    /** 觸發點擊 景點 */
    val mAttractionsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 最新消息 */
    val mNewsClickLiveData = MutableLiveData<Boolean>()
    /** 觸發點擊 景點收藏 */
    val mAttractionsCollectionClickLiveData = MutableLiveData<Boolean>()

    /** 景點列表 資料的 LiveData */
    val mAttractionsBeanListLiveData = MutableLiveData<List<AttractionsBean>>()


    /** 景點列表itemView 點擊事件 的LiveData*/
    val mAttractionsListItemClickLiveData = MutableLiveData<AttractionsBean>()
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
    /** 當點擊 景點列表的 收藏 */
    fun onAttractionsListCheckedChangeListener(attractionsBean: AttractionsBean,isChecked: Boolean){

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

    // MARK:- ========================== Method
    /** 呼叫 取得 景點列表 */
    fun callGetAttractionsList(page: String){
        mIsLoadingLiveData.value = true

        mainRepository.callGetNewsList("zh-tw"
            ,null
            ,null
            ,null
            ,page
            ,object: IOnOptionListener<ApiBase.GetAttractionsList.Response>{
                override fun onExecute(option: ApiBase.GetAttractionsList.Response?) {
                    //總頁數計算
                    val totalCount = option?.total
                    if (totalCount != null) {
                        var totalPage = totalCount/CubeTravelConfig.COUNT_EVERY_PAGE_DATA
                        if (totalCount%CubeTravelConfig.COUNT_EVERY_PAGE_DATA != 0){
                            totalPage ++
                        }
                        mAttractionsListTotalPage = totalPage.toString()
                    }
                    //刷新 當前資料 頁碼紀錄
                    mAttractionsListNowPage = page

                    //預計套到recyclerview的資料List
                    var willUseAttractionsBeanList: MutableList<AttractionsBean> = mutableListOf()
                    //當前UI中的資料List
                    var nowUIAttractionsBeanList = mAttractionsBeanListLiveData.value
                    if (nowUIAttractionsBeanList == null){
                        nowUIAttractionsBeanList = mutableListOf()
                    }
                    //剛從API取得的資料List
                    var apiAttractionsBeanList = option?.data
                    if (apiAttractionsBeanList.isNullOrEmpty()){
                        apiAttractionsBeanList = mutableListOf()
                    }
                    willUseAttractionsBeanList.addAll(nowUIAttractionsBeanList)
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
    /** 重置景點列表資料 */
    fun resetAttractionsData(){
        mAttractionsListTotalPage = "1"

        mAttractionsListNowPage = "1"

        mAttractionsBeanListLiveData.value = mutableListOf()
    }
}