package com.ci.v1_ci_view.ui.view.toolbar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.view.imageview.CIImageView

/** CIToolbar */
open class CIToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {
    companion object {
        @JvmStatic
        @BindingAdapter("title_text")
        fun setTitleText(view: CIToolbar, title: String?) {
            view.mTitleTextView.text = title
        }
    }


    // MARK:-================= View
    private val mMainLayout : FrameLayout by lazy {
        findViewById(R.id.layout_toolbar)
    }
    /** 標題 */
    private val mTitleTextView : AppCompatTextView by lazy {
        findViewById(R.id.text_title)
    }
    /** 返回 */
    val mBackImageView : AppCompatImageView by lazy {
        findViewById(R.id.image_back)
    }
    // MARK:-================= Data

    // MARK:-================= Event


    // MARK:-================= Method
    init {

        inflate(context, R.layout.view_ci_toolbar, this)

        if (attrs != null) {
            val attributes = context
                .theme
                .obtainStyledAttributes(
                    attrs
                    , R.styleable.CIToolbar
                    , 0
                    , 0
            )
            //背景色
            mMainLayout.background = attributes.getDrawable(R.styleable.CIToolbar_android_background)
            //title文字
            mTitleTextView.text = attributes.getText(R.styleable.CIToolbar_title_text)
            //title文字
            mTitleTextView.setTextColor(attributes.getColor(R.styleable.CIToolbar_title_text_color,
                Color.BLACK))
            //左邊圖示是否顯示(返回圖)
            setBackImageViewVisible(attributes.getBoolean(R.styleable.CIToolbar_showLeftImage,false))

        }

    }

    fun setBackImageViewVisible(visible: Boolean) {

        mBackImageView.visibility = if (visible) View.VISIBLE else View.GONE
    }

}