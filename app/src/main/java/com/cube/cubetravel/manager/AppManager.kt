package com.cube.cubetravel.manager

import android.content.Context
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.config.CubeTravelConfig
import com.google.gson.Gson
import java.lang.ref.WeakReference
/** AppManager */
class AppManager(context: Context) {
    val mContext = WeakReference(context)
    val mSharedPreferences by lazy {
        mContext.get()?.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
    }

    object JsonKey {
        const val LanguageBean = "LanguageBean"
        const val UiMode = "UiMode"

    }
    /** 語系 */
    var mLanguageBean: LanguageBean
        get() {
            val value = mSharedPreferences?.getString(JsonKey.LanguageBean, null)
            if (value.isNullOrEmpty()) {
                val languageBean = LanguageBean()
                languageBean.language = CubeTravelConfig.DEFAULT_LANGUAGE_LANGUAGE
                languageBean.value = CubeTravelConfig.DEFAULT_LANGUAGE_VALUE
                languageBean.image = CubeTravelConfig.DEFAULT_LANGUAGE_IMAGE
                languageBean.appResourceCode = CubeTravelConfig.DEFAULT_LANGUAGE_APP_RESOURCE_CODE

                return languageBean
            }

            return Gson().fromJson(value, LanguageBean::class.java)
        }
        set(value) {
            mSharedPreferences?.edit()?.putString(JsonKey.LanguageBean, Gson().toJson(value))?.apply()
        }
    /** 用於紀錄當前的顏色模式 */
    var mUiMode: String?
        get() {
            return mSharedPreferences?.getString(JsonKey.UiMode, null)
        }
        set(value) {
            mSharedPreferences?.edit()?.putString(JsonKey.UiMode, value)?.apply()
        }
}