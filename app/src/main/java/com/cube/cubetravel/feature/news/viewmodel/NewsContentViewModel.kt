package com.cube.cubetravel.feature.news.viewmodel

import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
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

    // MARK:- ========================== Event
    /** 當點擊前往 */
    fun onGoToClick(){

    }
    // MARK:- ========================== Method

}