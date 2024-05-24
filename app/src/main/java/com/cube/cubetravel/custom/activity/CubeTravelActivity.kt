package com.cube.cubetravel.custom.activity

import android.os.Bundle
import android.widget.FrameLayout
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.ci.v1_ci_view.ui.view.toolbar.CIToolbar
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.viewmodel.BaseViewModel

abstract class CubeTravelActivity<T>: CIActivity<T>() {

    // MARK:- ========================== Life


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        //====================== Event
        //onToolbarBackClick
        mToolbar?.mBackImageView?.setOnClickListener {
            onToolbarBackClick()
        }
        //====================== Observe
        //onMsgLiveDataChanged
        mBaseViewModel().mMsgLiveData.observe(this) {
            onMsgLiveDataChanged(it)
        }
        //onIsLoadingLiveDataChanged
        mBaseViewModel().mIsLoadingLiveData.observe(this) {
            onIsLoadingLiveDataChanged(it)
        }
    }
    // MARK:- ========================== View
    /** toolbar */
    private val mToolbar : CIToolbar? by lazy {
        findViewById(R.id.toolbar)
    }

    // MARK:- ========================== Data
    abstract fun mBaseViewModel(): BaseViewModel
    /** xml中 最外圍的那層layout */
    override fun getSupperLayout(): FrameLayout {
        return findViewById(R.id.layout_main)
    }
    // MARK:- ========================== Event
    /** 當點擊 toolbar的back */
    private fun onToolbarBackClick(){
        finish()
    }
    // MARK:- ========================== Observe
    /** toast 觀察者監聽 */
    private fun onMsgLiveDataChanged(msg: String){
        showToast(msg)
    }
    /** loading 觀察者監聽*/
    private fun onIsLoadingLiveDataChanged(boolean: Boolean){
        setLoading(boolean)
    }

    // MARK:- ========================== Method
}