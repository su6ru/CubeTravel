package com.cube.cubetravel.feature.attractions_collection

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ci.v1_ci_view.ui.util.CIIntentUtil
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.factory.AttractionsCollectionContentViewModelFactory
import com.cube.cubetravel.data.factory.AttractionsContentViewModelFactory
import com.cube.cubetravel.databinding.ActivityAttractionsCollectionContentBinding
import com.cube.cubetravel.databinding.ActivityAttractionsContentBinding
import com.cube.cubetravel.feature.attractions.viewmodel.AttractionsContentViewModel
import com.cube.cubetravel.feature.attractions_collection.viewmodel.AttractionsCollectionContentViewModel
import com.cube.cubetravel.feature.web.WebActivity

/** 景點收藏內頁 */
class AttractionsCollectionContentActivity : CubeTravelActivity<AttractionsCollectionBean>() {
    // MARK:- ========================== Define

    companion object{
        fun startActivity(activity: CubeTravelActivity<*>, attractionsCollectionBean: AttractionsCollectionBean){
            CIActivity.startActivity(activity,AttractionsCollectionContentActivity::class.java,attractionsCollectionBean)
        }

    }
    override fun mBaseViewModel(): BaseViewModel {
        return mAttractionsCollectionContentViewModel
    }
    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //====================== DataBinding
        setContentView(mActivityAttractionsCollectionContentBinding.root)
        mActivityAttractionsCollectionContentBinding.viewmodel = mAttractionsCollectionContentViewModel
        mActivityAttractionsCollectionContentBinding.lifecycleOwner = this
        lifecycle.addObserver(mAttractionsCollectionContentViewModel)


        //====================== Observe
        //onImageBannerBeanListChanged
        mAttractionsCollectionContentViewModel.mImageBannerBeanListLiveData.observe(this) {
            onImageBannerBeanListChanged(it)
        }
        //onMapClickChanged
        mAttractionsCollectionContentViewModel.mMapClickLiveData.observe(this) {
            onMapClickChanged(it)
        }
        //onGoToWebClickChanged
        mAttractionsCollectionContentViewModel.mGoToWebClickLiveData.observe(this) {
            onGoToWebClickChanged(it)
        }
        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityAttractionsCollectionContentBinding: ActivityAttractionsCollectionContentBinding by lazy {
        ActivityAttractionsCollectionContentBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    private val mAttractionsCollectionContentViewModel: AttractionsCollectionContentViewModel by lazy {
        ViewModelProvider(this, AttractionsCollectionContentViewModelFactory(getIntentData(AttractionsCollectionBean::class.java)))[AttractionsCollectionContentViewModel::class.java]
    }
    // MARK:- ========================== Observe
    /** 圖片 Banner資料變動觀察,如果有變動就更新view  */
    private fun onImageBannerBeanListChanged(bannerBeans: List<ImageBannerBean>) {
        if (bannerBeans.isEmpty()){
            mActivityAttractionsCollectionContentBinding.bannerImage.visibility = View.GONE
        }else{
            mActivityAttractionsCollectionContentBinding.bannerImage.visibility = View.VISIBLE

            mActivityAttractionsCollectionContentBinding.bannerImage.submitList(bannerBeans)

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