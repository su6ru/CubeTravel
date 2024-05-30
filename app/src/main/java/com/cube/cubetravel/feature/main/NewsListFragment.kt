package com.cube.cubetravel.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci.v1_ci_view.ui.listener.IOnOptionCheckedChangedListener
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.view.fragment.CIFragment
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.databinding.FragmentNewsListBinding
import com.cube.cubetravel.feature.main.adapter.NewsListAdapter
import com.cube.cubetravel.feature.main.diffcallback.NewsListDiffCallback
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.NewsContentActivity

/** 最新消息 列表 Fragment */
class NewsListFragment: CIFragment(R.layout.fragment_news_list) {
    // MARK:- ========================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mFragmentNewsListBinding = FragmentNewsListBinding.inflate(layoutInflater)

        return mFragmentNewsListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //====================== DataBinding
        mFragmentNewsListBinding.viewmodel = mMainViewModel
        mFragmentNewsListBinding.lifecycleOwner = this

        //====================== recyclerview
        mFragmentNewsListBinding.recyclerview.layoutManager = LinearLayoutManager(CubeTravelApplication.INSTANCE)
        mNewsListAdapter = NewsListAdapter(NewsListDiffCallback(),mOnListItemClick)
        mFragmentNewsListBinding.recyclerview.adapter = mNewsListAdapter

        //====================== Observe
        //onNewsBeanListObserve
        mMainViewModel.mNewsBeanListLiveData.observe(viewLifecycleOwner,object :
            Observer<List<NewsBean>> {
            override fun onChanged(value: List<NewsBean>) {
                onNewsBeanListObserve(value)
            }
        })
        //onNewsListItemClickObserve
        mMainViewModel.mNewsListItemClickLiveData.observe(viewLifecycleOwner,object :
            Observer<NewsBean> {
            override fun onChanged(value: NewsBean) {
                onNewsListItemClickObserve(value)
            }
        })

    }

    // MARK: - ========================== Data
    /** DataBinding */
    private lateinit var mFragmentNewsListBinding: FragmentNewsListBinding
    /** ViewModel */
    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    /** NewsListAdapter */
    private lateinit var mNewsListAdapter : NewsListAdapter
    // MARK: - ========================== Event
    /** 點擊 最新消息列表 的 任一 itemView */
    private val mOnListItemClick : IOnOptionListener<NewsBean> = object :
        IOnOptionListener<NewsBean> {
        override fun onExecute(option: NewsBean?) {
            mMainViewModel.onNewsListItemClick(option!!)
        }

    }
    // MARK:- ========================== Observe
    /** 觀察 最新消息列表資料 發生變化 */
    private fun onNewsBeanListObserve(value: List<NewsBean>){
        mNewsListAdapter.submitList(value)
    }

    /** 觀察 當點擊 最新消息列表的item */
    private fun onNewsListItemClickObserve(value: NewsBean){
        val activity = getMyActivity()
        if (activity != null){
            if (activity is CubeTravelActivity<*>){
                NewsContentActivity.startActivity(activity,value)
            }

        }

    }
}