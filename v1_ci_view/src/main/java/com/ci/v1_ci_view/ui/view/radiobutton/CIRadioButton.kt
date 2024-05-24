package com.ci.v1_ci_view.ui.view.radiobutton

import android.content.Context
import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.databinding.BindingAdapter
import com.ci.v1_ci_view.ui.listener.IOnOptionListener

class CIRadioButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatRadioButton(context,attrs) {
    companion object{
        @JvmStatic
        @BindingAdapter("onCheckedChanged")
        fun setOnCheckedChanged(view: CIRadioButton , checkedChangeListener: CompoundButton.OnCheckedChangeListener?){
            if (checkedChangeListener != null){
                view.setOnCheckedChangeListener(checkedChangeListener)
            }
        }
    }
}