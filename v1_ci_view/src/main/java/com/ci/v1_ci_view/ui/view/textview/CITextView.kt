package com.ci.v1_ci_view.ui.view.textview

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.util.CIResourceUtil

/** CITextView */
class CITextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        //設定 drawableStart drawableEnd drawableTop drawableBottom 的 drawable 高度
        var drawableHeight = 30f
        //設定 drawableStart drawableEnd drawableTop drawableBottom 的 drawable 寬度
        var drawableWidth = 30f

        if (attrs != null){
            val attributes = context
                .theme
                .obtainStyledAttributes(
                    attrs
                    , R.styleable.CITextView
                    ,0
                    ,0)
            drawableHeight = attributes.getFloat(R.styleable.CITextView_drawable_height,30f)
            drawableWidth = attributes.getFloat(R.styleable.CITextView_drawable_width,30f)
        }

        post {
            //設定 drawableStart  drawableEnd drawableTop drawableBottom 的 drawable大小
            val compoundDrawablesList = compoundDrawables
            for (i in 0..3){
                val drawable = compoundDrawablesList[i]
                if (drawable != null){
                    drawable.bounds = Rect(0,0,
                        CIResourceUtil.Companion.getPxByDp(drawableWidth),CIResourceUtil.Companion.getPxByDp(drawableHeight))
                }
            }
            setCompoundDrawables(compoundDrawablesList[0]
                ,compoundDrawablesList[1]
                ,compoundDrawablesList[2]
                ,compoundDrawablesList[3])


        }

    }
}