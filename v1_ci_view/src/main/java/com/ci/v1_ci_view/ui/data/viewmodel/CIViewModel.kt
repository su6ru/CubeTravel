package com.ci.v1_ci_view.ui.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CIViewModel : ViewModel() {
    /** 傳遞UILoading 控制 用的 liveData */
    var mIsLoadingLiveData  = MutableLiveData<Boolean>()
    /** Loading計數 */
    var mLoadingCount = 0
    /** 控制是否顯示loading */
    fun setLoading(boolean: Boolean) {
        if (boolean) {
            mLoadingCount++
            mIsLoadingLiveData.value = true
        }else{
            mLoadingCount--
            if(mLoadingCount <= 0){
                mLoadingCount = 0
                mIsLoadingLiveData.value = false
            }
        }
    }

    /** String msg值 >>用於ui顯示訊息 */
    var mMsgLiveData = MutableLiveData<String>()


}