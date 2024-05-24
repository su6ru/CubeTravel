package com.ci.v1_ci_view.ui.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RawRes
import java.io.InputStream

class CIUtil {
    companion object {
        /** 隱藏軟體鍵盤  */
        fun hideSoftKeyboard(v: View?) {
            if (v == null) {
                return
            }
            val manager =
                v.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    ?: return

            manager.hideSoftInputFromWindow(v.windowToken, 0)
        }

        /** String字串防呆引用 */
        fun mergeStr(str: String?): String {
            return if (str.isNullOrEmpty()){
                ""
            }else{
                str
            }
        }
        /** 顯示toast */
        fun showToast(context: Context, content: String){
            Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
        }


    }
}