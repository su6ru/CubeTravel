package com.cube.cubetravel.feature.language

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.factory.LanguageListViewModelFactory
import com.cube.cubetravel.databinding.ActivityLanguageListBinding
import com.cube.cubetravel.feature.language.adapter.LanguageListAdapter
import com.cube.cubetravel.feature.language.diffcallback.LanguageListDiffCallback
import com.cube.cubetravel.feature.language.viewmodel.LanguageListViewModel

/** 語言 內頁 */
class LanguageListActivity : CubeTravelActivity<NewsBean>() {
    // MARK:- ========================== Define

    companion object{
        fun startActivity(activity: CubeTravelActivity<*>){
            CIActivity.startActivity(activity,LanguageListActivity::class.java,null)
        }

    }
    override fun mBaseViewModel(): BaseViewModel {
        return mLanguageListViewModel
    }
    // MARK:- ========================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //====================== DataBinding
        setContentView(mActivityLanguageListBinding.root)
        mActivityLanguageListBinding.viewmodel = mLanguageListViewModel
        mActivityLanguageListBinding.lifecycleOwner = this
        lifecycle.addObserver(mLanguageListViewModel)

        //====================== recyclerview
        mActivityLanguageListBinding.recyclerview.layoutManager = LinearLayoutManager(
            CubeTravelApplication.INSTANCE)
        mLanguageListAdapter = LanguageListAdapter(LanguageListDiffCallback(),mOnLanguageListItemClick)
        mActivityLanguageListBinding.recyclerview.adapter = mLanguageListAdapter
        //====================== Observe
        //onLanguageBeanListObserve
        mLanguageListViewModel.mLanguageBeanListLiveData.observe(this) {
            onLanguageBeanListObserve(it)
        }
        //onLanguageListItemClickObserve
        mLanguageListViewModel.mLanguageListItemClickLiveData.observe(this){
            onLanguageListItemClickObserve(it)
        }
        //====================== Init
    }

    // MARK:- ========================== View

    // MARK:- ========================== Data
    /** Data Binding */
    private val mActivityLanguageListBinding: ActivityLanguageListBinding by lazy {
        ActivityLanguageListBinding.inflate(layoutInflater)
    }
    /** ViewModel */
    val mLanguageListViewModel: LanguageListViewModel by lazy {
        ViewModelProvider(this, LanguageListViewModelFactory(CubeTravelApplication.INSTANCE))[LanguageListViewModel::class.java]
    }
    /** LanguageListAdapter */
    lateinit var mLanguageListAdapter : LanguageListAdapter
    // MARK: - ========================== Event
    /** 點擊 語言列表 的 任一 itemView */
    val mOnLanguageListItemClick : IOnOptionListener<LanguageBean> = object :
        IOnOptionListener<LanguageBean> {
        override fun onExecute(option: LanguageBean?) {
            mLanguageListViewModel.onLanguageListItemClick(option!!)
        }

    }
    // MARK:- ========================== Observe
    /** 觀察 語言列表資料 發生變化 */
    fun onLanguageBeanListObserve(value: List<LanguageBean>){
        mLanguageListAdapter.submitList(value)
    }

    /** 觀察 當點擊 語言列表的item */
    fun onLanguageListItemClickObserve(value: LanguageBean){


    }






}