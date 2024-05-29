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
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.databinding.FragmentAttractionsCollectionListBinding
import com.cube.cubetravel.databinding.FragmentAttractionsListBinding
import com.cube.cubetravel.feature.attractions.AttractionsContentActivity
import com.cube.cubetravel.feature.main.adapter.AttractionsCollectionListAdapter
import com.cube.cubetravel.feature.main.adapter.AttractionsListAdapter
import com.cube.cubetravel.feature.main.diffcallback.AttractionsCollectionListDiffCallback
import com.cube.cubetravel.feature.main.diffcallback.AttractionsListDiffCallback
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel

/** 收藏景點 列表 Fragment */
class AttractionsCollectionListFragment: CIFragment(R.layout.fragment_attractions_collection_list) {
    // MARK:- ========================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mFragmentAttractionsCollectionListBinding = FragmentAttractionsCollectionListBinding.inflate(layoutInflater)

        return mFragmentAttractionsCollectionListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //====================== DataBinding
        mFragmentAttractionsCollectionListBinding.viewmodel = mMainViewModel
        mFragmentAttractionsCollectionListBinding.lifecycleOwner = this

        //====================== recyclerview
        mFragmentAttractionsCollectionListBinding.recyclerview.layoutManager = LinearLayoutManager(CubeTravelApplication.INSTANCE)
        mAttractionsCollectionListAdapter = AttractionsCollectionListAdapter(AttractionsCollectionListDiffCallback(),mOnListItemClick,mOnCheckedChangeListener)
        mFragmentAttractionsCollectionListBinding.recyclerview.adapter = mAttractionsCollectionListAdapter

        //====================== Observe
        //onAttractionsCollectionBeanListObserve
        mMainViewModel.mAttractionsCollectionBeanListLiveData.observe(viewLifecycleOwner){
            onAttractionsCollectionBeanListObserve(it)
        }
        //onAttractionsListItemClickObserve
        mMainViewModel.mAttractionsCollectionListItemClickLiveData.observe(viewLifecycleOwner){
            onAttractionsCollectionListItemClickObserve(it)
        }

    }

    // MARK: - ========================== Data
    /** DataBinding */
    private lateinit var mFragmentAttractionsCollectionListBinding: FragmentAttractionsCollectionListBinding
    /** ViewModel */
    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    /** AttractionsListAdapter */
    private lateinit var mAttractionsCollectionListAdapter : AttractionsCollectionListAdapter
    // MARK: - ========================== Event
    /** 點擊 景點列表 的 任一 itemView */
    private val mOnListItemClick : IOnOptionListener<AttractionsCollectionBean> = object :
        IOnOptionListener<AttractionsCollectionBean> {
        override fun onExecute(option: AttractionsCollectionBean?) {
            mMainViewModel.onAttractionsCollectionListItemClick(option!!)
        }

    }
    /** 點擊 景點列表 的 收藏 */
    private val mOnCheckedChangeListener : IOnOptionCheckedChangedListener<AttractionsCollectionBean> = object :IOnOptionCheckedChangedListener<AttractionsCollectionBean> {
        override fun onExecute(option: AttractionsCollectionBean, isChecked: Boolean) {
            mMainViewModel.onAttractionsCollectionListFavoriteCheckedChangeListener(option,isChecked)
        }
    }
    // MARK:- ========================== Observe
    /** 觀察 景點列表資料 發生變化 */
    private fun onAttractionsCollectionBeanListObserve(value: List<AttractionsCollectionBean>){
        mAttractionsCollectionListAdapter.submitList(value)
    }

    /** 觀察 當點擊 景點列表的item */
    private fun onAttractionsCollectionListItemClickObserve(value: AttractionsCollectionBean){
        val activity = getMyActivity()
        if (activity != null){
            if (activity is CubeTravelActivity<*>){
           //     AttractionsContentActivity.startActivity(activity,value)
            }

        }

    }
}