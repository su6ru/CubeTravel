package com.ci.v1_ci_view.ui.listener

interface IOnOptionCheckedChangedListener<Option> {
    fun onExecute(option: Option, isChecked: Boolean)
}