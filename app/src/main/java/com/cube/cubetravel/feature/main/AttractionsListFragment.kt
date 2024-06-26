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
import com.cube.cubetravel.databinding.FragmentAttractionsListBinding
import com.cube.cubetravel.feature.attractions.AttractionsContentActivity
import com.cube.cubetravel.feature.main.adapter.AttractionsListAdapter
import com.cube.cubetravel.feature.main.diffcallback.AttractionsListDiffCallback
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import okhttp3.internal.notify

/** 景點 列表 Fragment */
class AttractionsListFragment: CIFragment(R.layout.fragment_attractions_list) {
    // MARK:- ========================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mAttractionsListFragmentBinding = FragmentAttractionsListBinding.inflate(layoutInflater)

        return mAttractionsListFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //====================== DataBinding
        mAttractionsListFragmentBinding.viewmodel = mMainViewModel
        mAttractionsListFragmentBinding.lifecycleOwner = this

        //====================== recyclerview
        mAttractionsListFragmentBinding.recyclerview.layoutManager = LinearLayoutManager(CubeTravelApplication.INSTANCE)
        mAttractionsListAdapter = AttractionsListAdapter(AttractionsListDiffCallback(),mOnListItemClick,mOnCheckedChangeListener)
        mAttractionsListFragmentBinding.recyclerview.adapter = mAttractionsListAdapter

        //====================== Observe
        //onAttractionsBeanListObserve
        mMainViewModel.mAttractionsBeanListLiveData.observe(viewLifecycleOwner){
            onAttractionsBeanListObserve(it)
        }
        //onAttractionsListItemClickObserve
        mMainViewModel.mAttractionsListItemClickLiveData.observe(viewLifecycleOwner){
            onAttractionsListItemClickObserve(it)
        }

    }

    // MARK: - ========================== Data
    /** DataBinding */
    private lateinit var mAttractionsListFragmentBinding: FragmentAttractionsListBinding
    /** ViewModel */
    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    /** AttractionsListAdapter */
    private lateinit var mAttractionsListAdapter : AttractionsListAdapter
    // MARK: - ========================== Event
    /** 點擊 景點列表 的 任一 itemView */
    private val mOnListItemClick : IOnOptionListener<AttractionsBean> = object :
        IOnOptionListener<AttractionsBean> {
        override fun onExecute(option: AttractionsBean?) {
            mMainViewModel.onAttractionsListItemClick(option!!)
        }

    }
    /** 點擊 景點列表 的 收藏 */
    private val mOnCheckedChangeListener : IOnOptionCheckedChangedListener<AttractionsBean> = object :IOnOptionCheckedChangedListener<AttractionsBean> {
        override fun onExecute(option: AttractionsBean, isChecked: Boolean) {
            mMainViewModel.onAttractionsListFavoriteCheckedChangeListener(option,isChecked)
        }
    }
    // MARK:- ========================== Observe
    /** 觀察 景點列表資料 發生變化 */
    private fun onAttractionsBeanListObserve(value: List<AttractionsBean>){
        if (value.isEmpty()){
            mAttractionsListFragmentBinding.textNodata.visibility = View.VISIBLE
        }else{
            mAttractionsListFragmentBinding.textNodata.visibility = View.GONE
        }
        mAttractionsListAdapter.submitList(value)
    }

    /** 觀察 當點擊 景點列表的item */
    private fun onAttractionsListItemClickObserve(value: AttractionsBean){
        val activity = getMyActivity()
        if (activity != null){
            if (activity is CubeTravelActivity<*>){
                AttractionsContentActivity.startActivity(activity,value)
            }

        }

    }
}