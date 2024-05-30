package com.cube.cubetravel.feature.web

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.GeolocationPermissions
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIFragmentUtil
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.factory.WebViewModelFactory
import com.cube.cubetravel.databinding.ActivityWebBinding
import com.cube.cubetravel.feature.web.viewmodel.WebViewModel
/** WebView */
class WebActivity : CubeTravelActivity<WebBean>() {
    // MARK:- ========================== Define

    companion object{
        fun startActivity(activity: CubeTravelActivity<*>,webBean: WebBean){
            CIActivity.startActivity(activity,WebActivity::class.java,webBean)
        }

    }
    override fun mBaseViewModel(): BaseViewModel {
        return mWebViewModel
    }
    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //====================== DataBinding
        setContentView(mActivityWebContentBinding.root)
        mActivityWebContentBinding.viewmodel = mWebViewModel
        mActivityWebContentBinding.lifecycleOwner = this
        lifecycle.addObserver(mWebViewModel)
        //====================== WebView
        mActivityWebContentBinding.webview.settings.javaScriptEnabled = true
        mActivityWebContentBinding.webview.settings.domStorageEnabled = true
        mActivityWebContentBinding.webview.settings.cacheMode = WebSettings.LOAD_DEFAULT

        mActivityWebContentBinding.webview.webChromeClient = object : WebChromeClient() {
            //授權使用定位請求
            override fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissions.Callback) {
                callback.invoke(origin, true, false)
            }
        }
        // 設置 WebViewClient
        mActivityWebContentBinding.webview.webViewClient = object : WebViewClient() {
            
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url ?: "")
                return true
            }
        }

        //====================== Observe
        //onWebBeanChanged
        mWebViewModel.mWebBeanLiveData.observe(this){
            onWebBeanChanged(it)
        }
        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityWebContentBinding: ActivityWebBinding by lazy {
        ActivityWebBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    private val mWebViewModel: WebViewModel by lazy {
        ViewModelProvider(this, WebViewModelFactory(getIntentData(WebBean::class.java)))[WebViewModel::class.java]
    }
    // MARK:- ========================== Event
    override fun onBackPressed() {
        if (mActivityWebContentBinding.webview.canGoBack()) {
            mActivityWebContentBinding.webview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // MARK:- ========================== Observe
    /** 觀察 WebBean資料是否變化 */
    private fun onWebBeanChanged(webBean: WebBean){
        mActivityWebContentBinding.webview.loadUrl(webBean.url)
    }
    // MARK:- ========================== Method







}