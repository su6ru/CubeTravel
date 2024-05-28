package com.cube.cubetravel.feature.main

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.util.CIFragmentUtil
import com.ci.v1_ci_view.ui.view.fragment.CIFragment
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.activity.CubeTravelActivity
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.databinding.FragmentSettingBinding
import com.cube.cubetravel.feature.language.LanguageListActivity
import com.cube.cubetravel.feature.main.viewmodel.MainViewModel
import com.cube.cubetravel.feature.news.NewsContentActivity

/** 設定 Fragment */
class SettingFragment: CIFragment(R.layout.fragment_setting) {
    // MARK:- ========================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mFragmentSettingBinding = FragmentSettingBinding.inflate(layoutInflater)

        return mFragmentSettingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //====================== DataBinding
        mFragmentSettingBinding.viewmodel = mMainViewModel
        mFragmentSettingBinding.lifecycleOwner = this

        mLanguageActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK) {
                onLanguageActivityResultEvent()

            }
        }

        //====================== Observe
        //onLanguageClickObserve
        mMainViewModel.mLanguageClickLiveData.observe(viewLifecycleOwner){
            onLanguageClickObserve(it)

        }


    }

    // MARK: - ========================== Data
    /** DataBinding */
    private lateinit var mFragmentSettingBinding: FragmentSettingBinding
    /** ViewModel */
    private val mMainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    private lateinit var mLanguageActivityResultLauncher: ActivityResultLauncher<Intent>
    // MARK: - ========================== Event
    /** 當從語言選擇返回 */
    private fun onLanguageActivityResultEvent(){
        //當觸發語言更換,重新讀取全部資料
        mMainViewModel.reloadAllListData()
    }
    // MARK:- ========================== Observe
    /** 觀察當前是否 點擊 語言 */
    private fun onLanguageClickObserve(boolean: Boolean) {
        val activity = getMyActivity()
        if (activity != null){
            if (activity is CubeTravelActivity<*>){
                LanguageListActivity.startActivity(activity,mLanguageActivityResultLauncher)

            }

        }
    }
}
