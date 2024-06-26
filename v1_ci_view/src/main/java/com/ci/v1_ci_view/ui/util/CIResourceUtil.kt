package com.ci.v1_ci_view.ui.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.RawRes
import java.io.InputStream

class CIResourceUtil {
    companion object{
        /** 讀取.json本地資料，並以string方式回傳  */
        fun byRawResource(context: Context, @RawRes id: Int): String {
            var in_s: InputStream? = null
            try {
                val res = context.resources

                in_s = res.openRawResource(id)

                val b = ByteArray(in_s.available())

                in_s.read(b)

                return String(b)
            } catch (e: Exception) {
                return ""
            } finally {
                try {
                    in_s?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        /** 取得DP  */
        fun getPxByDp(dp: Float): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        /** 取得PX  */
        fun getPx(unit: Int, `val`: Float): Float {
            return TypedValue.applyDimension(unit, `val`, Resources.getSystem().displayMetrics)
        }
        /** 目前是否為深色模式 */
        fun isNightModeActive(context: Context): Boolean {
            val nightModeFlags = context.getResources().getConfiguration().uiMode and Configuration.UI_MODE_NIGHT_MASK
            return nightModeFlags == Configuration.UI_MODE_NIGHT_YES
        }
    }
}