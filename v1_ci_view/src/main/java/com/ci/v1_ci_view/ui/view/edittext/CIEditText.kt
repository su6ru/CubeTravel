package com.ci.v1_ci_view.ui.view.edittext

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CIEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    fun isMailInputType(boolean: Boolean){
        if(boolean) {
            val filter = InputFilter { source, _, _, _, _, _ ->
                if (source != null && !source.matches("[a-zA-Z0-9@.]*".toRegex())) {
                    ""
                } else {
                    null
                }
            }
            filters = arrayOf(filter)
        }else{
            filters = arrayOf(null)
        }
    }

}