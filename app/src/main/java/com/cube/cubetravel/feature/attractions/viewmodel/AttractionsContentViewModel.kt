package com.cube.cubetravel.feature.attractions.viewmodel

import android.content.res.Resources
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.repository.AttractionsContentRepository

/** AttractionsActivity相關的  ViewModel*/
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
    /** 景點 資料的 LiveData */
    val mAttractionsBeanLiveData = MutableLiveData<AttractionsBean>()
    /** 景點 資料的 LiveData */
    val mImageBannerBeanListLiveData = MutableLiveData<MutableList<ImageBannerBean>>()

    /** 觸發點擊 導航 的 LiveData */
    val mMapClickLiveData = MutableLiveData<String>()
    /** 觸發點擊 前往網頁 的 LiveData */
    val mGoToWebClickLiveData = MutableLiveData<WebBean>()
    // MARK:- ========================== Event
    /** 當點擊 導航 */
    fun onMapClick(){
        val attractionsBean = mAttractionsBeanLiveData.value
        if (attractionsBean == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
            return
        }
        val address = attractionsBean.address
        if (address.isNullOrEmpty()){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_address_not_provided)
            return
        }
        mMapClickLiveData.value = address

    }
    /** 當點擊 前往網頁 */
    fun onGoToWebClick(){

        val intentAttractionsBean = attractionsContentRepository.getLastActivityIntentData()

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
        webBean.title = intentAttractionsBean.name ?: ""
        webBean.url = url

        mGoToWebClickLiveData.value = webBean
    }
    // MARK:- ========================== Method

}