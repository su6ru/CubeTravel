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
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers

/** MainActivity相關的  ViewModel*/
class MainViewModel(private val mainRepository: MainRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        callGetAttractionsList()

    }

    // MARK:- ========================== Data

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
    /** 當點擊 radiobutton */
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
            callGetAttractionsList()
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
            callGetAttractionsList()
        }

    }
    /** 當點擊 景點列表 的itemView*/
    fun onAttractionsListItemClick(attractionsBean : AttractionsBean){
        mAttractionsListItemClickLiveData.value = attractionsBean
    }
    /** 當點擊 景點列表的 收藏 */
    fun onAttractionsListCheckedChangeListener(attractionsBean: AttractionsBean,isChecked: Boolean){

    }
    // MARK:- ========================== Method
    /** 呼叫 取得 景點列表 */
    fun callGetAttractionsList(){
        mIsLoadingLiveData.value = true

        mainRepository.callGetNewsList("zh-tw"
            ,null
            ,null
            ,null
            ,"1"
            ,object: IOnOptionListener<ApiBase.GetAttractionsList.Response>{
                override fun onExecute(option: ApiBase.GetAttractionsList.Response?) {

                    var attractionsBeanList = option?.data
                    if (attractionsBeanList.isNullOrEmpty()){
                        attractionsBeanList = mutableListOf()
                    }
                    mAttractionsBeanListLiveData.value = attractionsBeanList
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
}