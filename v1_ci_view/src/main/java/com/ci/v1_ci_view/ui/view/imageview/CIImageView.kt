package com.ci.v1_ci_view.ui.view.imageview

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.view.banner.CIMultipleImageBanner
import com.ci.v1_ci_view.ui.view.radiobutton.CIRadioButton


class CIImageView @JvmOverloads constructor(context: Context,attributeSet: AttributeSet?) :
    AppCompatImageView(context,attributeSet) {
    companion object{
        @JvmStatic
        @BindingAdapter("setImageUrl")
        fun setImageUrl(view: CIImageView, url: String?) {
            if (url.isNullOrEmpty()) {
                return
            }

            view.setImageView(url)
        }
    }


        fun setImageView(url: String){
            Glide
                .with(this)
                .load(url)
                .into(this)
        }

}