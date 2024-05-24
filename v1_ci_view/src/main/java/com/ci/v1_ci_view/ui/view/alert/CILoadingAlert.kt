package com.ci.v1_ci_view.ui.view.alert

import android.content.Context
import android.util.AttributeSet
import com.ci.v1_ci_view.R

class CILoadingAlert constructor(context: Context) : CIBaseAlert(context, layout = R.layout.alert_loading) {
    init {
        inflate(context, R.layout.alert_loading,null)
    }

}