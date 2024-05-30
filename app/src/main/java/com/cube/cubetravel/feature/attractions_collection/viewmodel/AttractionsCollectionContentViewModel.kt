package com.cube.cubetravel.feature.attractions_collection.viewmodel

import android.content.res.Resources
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.repository.AttractionsCollectionContentRepository
import com.cube.cubetravel.data.repository.AttractionsContentRepository

/** AttractionsCollectionActivity相關的  ViewModel*/
class AttractionsCollectionContentViewModel(private val attractionsCollectionContentRepository: AttractionsCollectionContentRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        val intentAttractionsCollectionBean = attractionsCollectionContentRepository.getLastActivityIntentData()

        mAttractionsCollectionBeanLiveData.value = intentAttractionsCollectionBean

        val imageBannerBeanList = attractionsCollectionContentRepository.getImageBannerBeanList()
        mImageBannerBeanListLiveData.value = imageBannerBeanList
    }

    // MARK:- ========================== Data
    /** 景點 資料的 LiveData */
    val mAttractionsCollectionBeanLiveData = MutableLiveData<AttractionsCollectionBean>()
    /** 景點 資料的 LiveData */
    val mImageBannerBeanListLiveData = MutableLiveData<MutableList<ImageBannerBean>>()

    /** 觸發點擊 導航 的 LiveData */
    val mMapClickLiveData = MutableLiveData<String>()
    /** 觸發點擊 前往網頁 的 LiveData */
    val mGoToWebClickLiveData = MutableLiveData<WebBean>()
    // MARK:- ========================== Event
    /** 當點擊 導航 */
    fun onMapClick(){
        val attractionsCollectionBean = mAttractionsCollectionBeanLiveData.value
        if (attractionsCollectionBean == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
            return
        }
        val address = attractionsCollectionBean.address
        if (address.isNullOrEmpty()){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_address_not_provided)
            return
        }
        mMapClickLiveData.value = address

    }
    /** 當點擊 前往網頁 */
    fun onGoToWebClick(){

        val intentAttractionsCollectionBean = attractionsCollectionContentRepository.getLastActivityIntentData()

        if (intentAttractionsCollectionBean == null){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_data_abnormal)
            return
        }

        val url = intentAttractionsCollectionBean.url
        if (url.isNullOrEmpty()){
            mMsgLiveData.value = Resources.getSystem().getString(R.string.msg_this_data_web_url_not_provided)
            return
        }
        val webBean = WebBean()
        webBean.title = intentAttractionsCollectionBean.name ?: ""
        webBean.url = url

        mGoToWebClickLiveData.value = webBean
    }
    // MARK:- ========================== Method

}