package com.cube.cubetravel.custom.activity

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatDelegate
import com.ci.v1_ci_view.ui.view.activity.CIActivity
import com.ci.v1_ci_view.ui.view.toolbar.CIToolbar
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.custom.viewmodel.BaseViewModel
import com.cube.cubetravel.feature.main.MainActivity
import java.util.Locale
/** 此專案內 全Activity的繼承類 */
abstract class CubeTravelActivity<T>: CIActivity<T>() {

    // MARK:- ========================== Life

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        //讀取指定語系檔
        val languageBean = CubeTravelApplication.INSTANCE.mAppManager.mLanguageBean
        val appResourceCode = languageBean.appResourceCode

        setLocale(this, appResourceCode)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        //讀取指定語系檔
        val languageBean = CubeTravelApplication.INSTANCE.mAppManager.mLanguageBean
        val appResourceCode = languageBean.appResourceCode

        setLocale(this, appResourceCode)


        //淺色 或 深色模式控制
        val nowUiMode = CubeTravelApplication.INSTANCE.mAppManager.mUiMode
        val currentNightMode = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            //當切換為淺色模式
            Configuration.UI_MODE_NIGHT_NO -> {
                //若當前顏色模式 與 原先紀錄的顏色模式不同,則存入新設定,並重啟APP
                if (nowUiMode != CubeTravelApplication.UI_MODE_LIGHT) {
                    CubeTravelApplication.INSTANCE.mAppManager.mUiMode = CubeTravelApplication.UI_MODE_LIGHT
                    val refresh = Intent(this, MainActivity::class.java)
                    startActivity(refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
            }
            //當切換為深色模式
            Configuration.UI_MODE_NIGHT_YES -> {
                //若當前顏色模式 與 原先紀錄的顏色模式不同,則存入新設定,並重啟APP
                if (nowUiMode != CubeTravelApplication.UI_MODE_NIGHT) {
                    CubeTravelApplication.INSTANCE.mAppManager.mUiMode = CubeTravelApplication.UI_MODE_NIGHT
                    val refresh = Intent(this, MainActivity::class.java)
                    startActivity(refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))

                }
            }
        }

    }


    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)


        //====================== Event
        //onToolbarBackClick
        mToolbar?.mBackImageView?.setOnClickListener {
            onToolbarBackClick()
        }
        //====================== Observe
        //onMsgLiveDataChanged
        mBaseViewModel().mMsgLiveData.observe(this) {
            onMsgLiveDataChanged(it)
        }
        //onIsLoadingLiveDataChanged
        mBaseViewModel().mIsLoadingLiveData.observe(this) {
            onIsLoadingLiveDataChanged(it)
        }
    }
    // MARK:- ========================== View
    /** toolbar */
    private val mToolbar : CIToolbar? by lazy {
        findViewById(R.id.toolbar)
    }

    // MARK:- ========================== Data
    abstract fun mBaseViewModel(): BaseViewModel
    /** xml中 最外圍的那層layout */
    override fun getSupperLayout(): FrameLayout {
        return findViewById(R.id.layout_main)
    }
    // MARK:- ========================== Event
    /** 當點擊 toolbar的back */
    private fun onToolbarBackClick(){
        finish()
    }
    // MARK:- ========================== Observe
    /** toast 觀察者監聽 */
    private fun onMsgLiveDataChanged(msg: String){
        showToast(msg)
    }
    /** loading 觀察者監聽*/
    private fun onIsLoadingLiveDataChanged(boolean: Boolean){
        setLoading(boolean)
    }

    // MARK:- ========================== Method
    /** 改變語系 */
    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

    }
}