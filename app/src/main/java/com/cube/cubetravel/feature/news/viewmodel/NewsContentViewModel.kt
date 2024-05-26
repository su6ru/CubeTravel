package com.cube.cubetravel.feature.news.viewmodel

import android.content.res.Resources
import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.data.repository.NewsContentRepository

/** NewsContentActivity相關的  ViewModel*/
class NewsContentViewModel(private val newsContentRepository: NewsContentRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        mNewsBeanLiveData.value = newsContentRepository.getLastActivityIntentData()
    }

    // MARK:- ========================== Data
    /** 最新消息 資料的 LiveData */
    val mNewsBeanLiveData = MutableLiveData<NewsBean>()

    /** 觸發點擊 前往網頁 的 LiveData */
    val mGoToWebClickLiveData = MutableLiveData<WebBean>()
    // MARK:- ========================== Event
    /** 當點擊 前往網頁 */
    fun onGoToWebClick(){

        val intentAttractionsBean = newsContentRepository.getLastActivityIntentData()

        if (intentAttractionsBean == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
            return
        }

        val url = intentAttractionsBean.url
        if (url.isNullOrEmpty()){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_this_data_web_url_not_provided)
            return
        }
        val webBean = WebBean()
        webBean.title = intentAttractionsBean.title ?: ""
        webBean.url = url

        mGoToWebClickLiveData.value = webBean
    }
    // MARK:- ========================== Method

}