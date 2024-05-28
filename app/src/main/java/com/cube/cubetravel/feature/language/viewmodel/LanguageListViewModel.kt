package com.cube.cubetravel.feature.language.viewmodel

import android.content.res.Resources
import android.widget.CompoundButton
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.data.repository.LanguageListRepository
import com.cube.cubetravel.data.repository.MainRepository
import com.cube.cubetravel.data.repository.NewsContentRepository

/** LanguageListActivity相關的  ViewModel*/
class LanguageListViewModel(private val languageListRepository: LanguageListRepository): BaseViewModel(),DefaultLifecycleObserver {
    // MARK:- ========================== Life
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mLanguageBeanListLiveData.value = languageListRepository.getLanguageBeanList()
    }

    // MARK:- ========================== Data
    /** 語言列表 資料的 LiveData */
    val mLanguageBeanListLiveData = MutableLiveData<List<LanguageBean>>()

    /** 設定的 語言列表 itemView 點擊事件 的LiveData*/
    val mLanguageListItemClickLiveData = MutableLiveData<LanguageBean>()
    // MARK:- ========================== Event
    /** 當點擊 設定列表 的itemView*/
    fun onLanguageListItemClick(languageBean : LanguageBean){
        mLanguageListItemClickLiveData.value = languageBean
    }
    // MARK:- ========================== Method

}