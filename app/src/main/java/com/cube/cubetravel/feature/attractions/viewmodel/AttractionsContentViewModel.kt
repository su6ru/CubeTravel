package com.cube.cubetravel.feature.attractions.viewmodel

import android.content.res.Resources
import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.AttractionsContentRepository
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.data.repository.NewsContentRepository

/** NewsContentActivity相關的  ViewModel*/
class AttractionsContentViewModel(private val attractionsContentRepository: AttractionsContentRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        val intentAttractionsBean = attractionsContentRepository.getLastActivityIntentData()

        mAttractionsBeanLiveData.value = intentAttractionsBean

        val imageBannerBeanList = attractionsContentRepository.getImageBannerBeanList()
        mImageBannerBeanListLiveData.value = imageBannerBeanList
    }

    // MARK:- ========================== Data
    /** 最新消息 資料的 LiveData */
    val mAttractionsBeanLiveData = MutableLiveData<AttractionsBean>()
    /** 最新消息 資料的 LiveData */
    val mImageBannerBeanListLiveData = MutableLiveData<MutableList<ImageBannerBean>>()

    /** 觸發點擊 導航 的 LiveData */
    val mMapClickLiveData = MutableLiveData<String>()
    // MARK:- ========================== Event
    /** 當點擊 導航 */
    fun onMapClick(){
        val attractionsBean = mAttractionsBeanLiveData.value
        if (attractionsBean == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_address_not_provided)
            return
        }
        val address = attractionsBean.address
        if (address.isNullOrEmpty()){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_address_not_provided)
            return
        }
        mMapClickLiveData.value = address

    }
    // MARK:- ========================== Method

}