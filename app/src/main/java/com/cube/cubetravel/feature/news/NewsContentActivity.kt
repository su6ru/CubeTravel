package com.cube.cubetravel.feature.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIFragmentUtil
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.factory.MainViewModelFactory
import com.cube.cubetravel.data.factory.NewsContentViewModelFactory
import com.cube.cubetravel.databinding.ActivityMainBinding
import com.cube.cubetravel.databinding.ActivityNewsContentBinding
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.viewmodel.NewsContentViewModel
import com.cube.cubetravel.feature.web.WebActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import java.io.IOException
/** 最新消息內頁 */
class NewsContentActivity : CubeTravelActivity<NewsBean>() {
    // MARK:- ========================== Define

    companion object{
        fun startActivity(activity: CubeTravelActivity<*>,newsBean: NewsBean){
            CIActivity.startActivity(activity,NewsContentActivity::class.java,newsBean)
        }

    }
    override fun mBaseViewModel(): BaseViewModel {
        return mNewsContentViewModel
    }
    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //====================== DataBinding
        setContentView(mActivityNewsContentBinding.root)
        mActivityNewsContentBinding.viewmodel = mNewsContentViewModel
        mActivityNewsContentBinding.lifecycleOwner = this
        lifecycle.addObserver(mNewsContentViewModel)


        //====================== Observe
        //onGoToWebClickChanged
        mNewsContentViewModel.mGoToWebClickLiveData.observe(this) {
            onGoToWebClickChanged(it)
        }
        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityNewsContentBinding: ActivityNewsContentBinding by lazy {
        ActivityNewsContentBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    private val mNewsContentViewModel: NewsContentViewModel by lazy {
        ViewModelProvider(this, NewsContentViewModelFactory(getIntentData(NewsBean::class.java)))[NewsContentViewModel::class.java]
    }
    // MARK:- ========================== Observe
    /** 觀察 當點擊 前往網頁 */
    private fun onGoToWebClickChanged(webBean: WebBean){

        WebActivity.startActivity(this,webBean)
    }
    // MARK:- ========================== Method







}