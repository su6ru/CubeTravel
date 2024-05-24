package com.ci.v1_ci_view.ui.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CIViewModel : ViewModel() {
    /** Loading 用的 liveData */
    var mIsLoadingLiveData  = MutableLiveData<Boolean>()
    /** String msg值 >>用於ui顯示訊息 */
    var mMsgLiveData = MutableLiveData<String>()
}