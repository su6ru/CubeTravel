package com.cube.cubetravel.custom.alert

import android.content.Context
import com.ci.v1_ci_view.ui.view.alert.CIBaseAlert
import com.cube.cubetravel.R

class ListItemAlert(context: Context) : CIBaseAlert(context, layout = R.layout.alert_list_item) {
    init {
        inflate(context, R.layout.alert_list_item,null)
    }

}