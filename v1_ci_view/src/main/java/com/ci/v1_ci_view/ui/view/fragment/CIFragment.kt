package com.ci.v1_ci_view.ui.view.fragment

import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ci.v1_ci_view.ui.view.activity.CIActivity

open class CIFragment(layoutId : Int) : Fragment(layoutId) {
    init {

    }
    // MARK:- ===================== Define

    // MARK:- ===================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("///", this.javaClass.simpleName + " onCreateView")

        return super.onCreateView(inflater, container, savedInstanceState)

    }


    // MARK:- ====================== View

    // MARK:- ====================== Data

    fun getMyActivity(): CIActivity<*>?{
        return activity.takeIf { it is CIActivity<*> } as? CIActivity<*>
    }
    // MARK:- ====================== Event

    // MARK:- ====================== Method



}