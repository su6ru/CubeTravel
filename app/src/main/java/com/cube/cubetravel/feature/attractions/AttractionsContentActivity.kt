package com.cube.cubetravel.feature.attractions

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIIntentUtil
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.factory.AttractionsContentViewModelFactory
import com.cube.cubetravel.databinding.ActivityAttractionsContentBinding
import com.cube.cubetravel.feature.attractions.viewmodel.AttractionsContentViewModel
import com.cube.cubetravel.feature.web.WebActivity

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
        //onImageBannerBeanListChanged
        mAttractionsContentViewModel.mImageBannerBeanListLiveData.observe(this) {
            onImageBannerBeanListChanged(it)
        }
        //onMapClickChanged
        mAttractionsContentViewModel.mMapClickLiveData.observe(this) {
            onMapClickChanged(it)
        }
        //onGoToWebClickChanged
        mAttractionsContentViewModel.mGoToWebClickLiveData.observe(this) {
            onGoToWebClickChanged(it)
        }
        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityAttractionsContentBinding: ActivityAttractionsContentBinding by lazy {
        ActivityAttractionsContentBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    private val mAttractionsContentViewModel: AttractionsContentViewModel by lazy {
        ViewModelProvider(this, AttractionsContentViewModelFactory(getIntentData(AttractionsBean::class.java)))[AttractionsContentViewModel::class.java]
    }
    // MARK:- ========================== Observe
    /** 圖片 Banner資料變動觀察,如果有變動就更新view  */
    private fun onImageBannerBeanListChanged(bannerBeans: List<ImageBannerBean>) {
        if (bannerBeans.isEmpty()){
            mActivityAttractionsContentBinding.bannerImage.visibility = View.GONE
        }else{
            mActivityAttractionsContentBinding.bannerImage.visibility = View.VISIBLE

            mActivityAttractionsContentBinding.bannerImage.submitList(bannerBeans)

        }
    }
    /** 觀察 當點擊 導航 */
    private fun onMapClickChanged(address: String){
        CIIntentUtil.openGoogleMapToLocation(this, address,0.0,0.0)
    }
    /** 觀察 當點擊 前往網頁 */
    private fun onGoToWebClickChanged(webBean: WebBean){

        WebActivity.startActivity(this,webBean)
    }
    // MARK:- ========================== Method







}