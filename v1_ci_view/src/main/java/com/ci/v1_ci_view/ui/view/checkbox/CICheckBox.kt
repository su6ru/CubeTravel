package com.ci.v1_ci_view.ui.view.checkbox

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.databinding.BindingAdapter
import com.ci.v1_ci_view.ui.listener.IOnOptionListener

class CICheckBox @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatCheckBox(context,attrs) {
    companion object{
        @JvmStatic
        @BindingAdapter("onCheckedChanged")
        fun setOnCheckedChanged(view: CICheckBox, checkedChangeListener: CompoundButton.OnCheckedChangeListener?){
            if (checkedChangeListener != null){
                view.setOnCheckedChangeListener(checkedChangeListener)
            }
        }
    }
}