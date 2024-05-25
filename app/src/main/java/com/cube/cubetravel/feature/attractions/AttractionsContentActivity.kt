package com.cube.cubetravel.feature.attractions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIFragmentUtil
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.factory.AttractionsContentViewModelFactory
import com.cube.cubetravel.data.factory.MainViewModelFactory
import com.cube.cubetravel.data.factory.NewsContentViewModelFactory
import com.cube.cubetravel.databinding.ActivityAttractionsContentBinding
import com.cube.cubetravel.databinding.ActivityMainBinding
import com.cube.cubetravel.databinding.ActivityNewsContentBinding
import com.cube.cubetravel.feature.attractions.viewmodel.AttractionsContentViewModel
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.viewmodel.NewsContentViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import java.io.IOException
/** 景點內頁 */
class AttractionsContentActivity : CubeTravelActivity<AttractionsBean>() {
    // MARK:- ========================== Define

    companion object{
        fun startActivity(activity: CubeTravelActivity<*>, attractionsBean: AttractionsBean){
            CIActivity.startActivity(activity,AttractionsContentActivity::class.java,attractionsBean)
        }

    }
    override fun mBaseViewModel(): BaseViewModel {
        return mAttractionsContentViewModel
    }
    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //====================== DataBinding
        setContentView(mActivityAttractionsContentBinding.root)
        mActivityAttractionsContentBinding.viewmodel = mAttractionsContentViewModel
        mActivityAttractionsContentBinding.lifecycleOwner = this
        lifecycle.addObserver(mAttractionsContentViewModel)


        //====================== Observe

        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityAttractionsContentBinding: ActivityAttractionsContentBinding by lazy {
        ActivityAttractionsContentBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    val mAttractionsContentViewModel: AttractionsContentViewModel by lazy {
        ViewModelProvider(this, AttractionsContentViewModelFactory(getIntentData(AttractionsBean::class.java)))[AttractionsContentViewModel::class.java]
    }
    // MARK:- ========================== Observe

    // MARK:- ========================== Method







}