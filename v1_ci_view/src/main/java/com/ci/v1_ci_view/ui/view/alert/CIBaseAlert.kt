package com.ci.v1_ci_view.ui.view.alert

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.util.CIUtil

/** 客製化Alert的繼承基礎類 */
open class CIBaseAlert @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @LayoutRes layout: Int = 0
) : FrameLayout(context,attributeSet) {

    init {
        inflate(context,layout,this)


        setBackgroundColor(-0x67000000)

        isClickable = true

        isFocusableInTouchMode = true

        isFocusable = true
        /*
        if (findViewById<View>(R.id.alert_content) != null) {
            findViewById<View>(R.id.alert_content).setOnClickListener(null)
        }
        */

        if (findViewById<View>(R.id.alert_parent) != null) {
            findViewById<View>(R.id.alert_parent).setOnClickListener(null)
        }


    }
    // MARK:- ========================== View
    /** 父view */
    public val mParentView : View by lazy {
        findViewById(R.id.alert_parent)
    }


    // MARK:- ========================== Data
    public var mIsShow = false
    fun isShow():Boolean{
        return mIsShow
    }
    // MARK:- ========================== Event
    /** 顯示alert */
    public fun show(parent : ViewGroup){
        if (mIsShow){
            return
        }
        parent.addView(this,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)

        if (requestFocus()){
            CIUtil.hideSoftKeyboard(this)
        }

        val am = AlphaAnimation(0.2f,1f).apply {
            this.duration = 200
        }

        mParentView.startAnimation(am)

        mIsShow = true

    }
    /** 關閉alert */
    public fun hide(){
        if (parent !is ViewGroup){
            return
        }
        val parentLayout = parent as ViewGroup

        if (requestFocus()){
            CIUtil.hideSoftKeyboard(this)
        }

        parentLayout.removeView(this)

        mIsShow = false

    }

    // MARK:- ========================== Method

}