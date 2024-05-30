package com.cube.cubetravel.feature.web.viewmodel

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
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
import com.cube.cubetravel.data.repository.WebRepository

/** WebActivity相關的  ViewModel*/
class WebViewModel(private val webRepository: WebRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        mWebBeanLiveData.value = webRepository.getLastActivityIntentData()
    }

    // MARK:- ========================== Data
    /** 網頁 資料的 LiveData */
    val mWebBeanLiveData = MutableLiveData<WebBean>()

    // MARK:- ========================== Event
    fun onWebViewPageStartedListener(){
        mIsLoadingLiveData.value = true
    }
    fun onWebViewFinishedListener(){
        mIsLoadingLiveData.value = false

    }
    fun onWebViewErrorListener(){
        mIsLoadingLiveData.value = true
    }

    // MARK:- ========================== Method

}