package com.cube.cubetravel.feature.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIFragmentUtil
import com.ci.v1_ci_view.ui.util.CIPermissionsUtil
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.factory.MainViewModelFactory
import com.cube.cubetravel.databinding.ActivityMainBinding
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import java.io.IOException
/** 首頁 */
class MainActivity : CubeTravelActivity<Void>() {
    // MARK:- ========================== Define
    companion object {
        /** Fragment Tag >>> 景點列表 */
        const val FRAGMENT_ATTRACTIONS_LIST = "FRAGMENT_ATTRACTIONS_LIST"
        /** Fragment Tag >>> 最新消息列表 */
        const val FRAGMENT_NEWS_LIST = "FRAGMENT_NEWS_LIST"
        /** Fragment Tag >>> 景點收藏列表 */
        const val FRAGMENT_ATTRACTIONS_COLLECTION_LIST = "FRAGMENT_ATTRACTIONS_COLLECTION_LIST"
        /** Fragment Tag >>> 設定 */
        const val FRAGMENT_SETTING = "FRAGMENT_SETTING"
    }

    override fun mBaseViewModel(): BaseViewModel {
        return mMainViewModel
    }


    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //====================== DataBinding
        setContentView(mActivityMainBinding.root)
        mActivityMainBinding.viewmodel = mMainViewModel
        mActivityMainBinding.lifecycleOwner = this
        lifecycle.addObserver(mMainViewModel)


        //====================== Observe
        //onAttractionsClickObserve
        mMainViewModel.mAttractionsClickLiveData.observe(this) { value ->
            onAttractionsClickObserve(value)
        }
        //onNewsClickObserve
        mMainViewModel.mNewsClickLiveData.observe(this) { value ->
            onNewsClickObserve(value)
        }
        //onAttractionsCollectionClickObserve
        mMainViewModel.mAttractionsCollectionClickLiveData.observe(this) { value ->
            onAttractionsCollectionClickObserve(value)
        }
        //onSettingClickObserve
        mMainViewModel.mSettingClickLiveData.observe(this) { value ->
            onSettingClickObserve(value)
        }
        //====================== Init
        //預設顯示 AttractionsListFragment
        CIFragmentUtil
            .switchFragment(this
                ,R.id.fragment
                ,mAttractionsListFragment
                ,FRAGMENT_ATTRACTIONS_LIST)

        CIPermissionsUtil.registerLocationPermission(this)
    }

    // MARK:- ========================== View
    /** 景點 列表 Fragment */
    val mAttractionsListFragment by lazy {
        AttractionsListFragment()
    }
    /** 最新消息 列表 Fragment */
    val mNewsListFragment by lazy {
        NewsListFragment()
    }
    /** 景點收藏 列表 Fragment */
    val mAttractionsCollectionListFragment by lazy {
        AttractionsCollectionListFragment()
    }
    /** 設定 Fragment */
    val mSettingFragment by lazy {
        SettingFragment()
    }
    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
    }
    // MARK:- ========================== Observe
    /** 觀察當前是否 點擊 景點 */
    private fun onAttractionsClickObserve(boolean: Boolean) {
        if (boolean) {
            CIFragmentUtil
                .switchFragment(this
                    ,R.id.fragment
                    ,mAttractionsListFragment
                    ,FRAGMENT_ATTRACTIONS_LIST)
        }

    }
    /** 觀察當前是否 點擊 最新消息 */
    private fun onNewsClickObserve(boolean: Boolean) {
        if (boolean) {
            CIFragmentUtil
                .switchFragment(this
                    ,R.id.fragment
                    ,mNewsListFragment
                    , FRAGMENT_NEWS_LIST)
        }
    }
    /** 觀察當前是否 點擊 景點收藏 */
    private fun onAttractionsCollectionClickObserve(boolean: Boolean) {
        if (boolean) {
            CIFragmentUtil
                .switchFragment(this
                    ,R.id.fragment
                    ,mAttractionsCollectionListFragment
                    , FRAGMENT_ATTRACTIONS_COLLECTION_LIST)
        }
    }
    /** 觀察當前是否 點擊 設定 */
    private fun onSettingClickObserve(boolean: Boolean) {
        if (boolean) {
            CIFragmentUtil
                .switchFragment(this
                    ,R.id.fragment
                    ,mSettingFragment
                    , FRAGMENT_SETTING)
        }
    }
    // MARK:- ========================== Method







}