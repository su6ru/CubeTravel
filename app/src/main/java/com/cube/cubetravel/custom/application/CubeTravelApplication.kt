package com.cube.cubetravel.custom.application

import android.content.res.Configuration
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.ci.v1_ci_view.ui.view.application.CIApplication
import com.cube.cubetravel.manager.AppManager
/** application */
class CubeTravelApplication: CIApplication() {
    companion object{
        lateinit var INSTANCE: CubeTravelApplication
        /** Fragment Tag >>> 景點收藏列表 */
        const val UI_MODE_LIGHT = "UI_MODE_LIGHT"
        /** Fragment Tag >>> 設定 */
        const val UI_MODE_NIGHT = "UI_MODE_NIGHT"
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        //紀錄 初次啟動 APP 的系統色系
        if (mAppManager.mUiMode == null){
            if (CIResourceUtil.isNightModeActive(this)){
                mAppManager.mUiMode = UI_MODE_NIGHT
            }else{
                mAppManager.mUiMode = UI_MODE_LIGHT

            }
        }

    }
    /** AppManager */
    val mAppManager by lazy {
        AppManager(this)
    }


}