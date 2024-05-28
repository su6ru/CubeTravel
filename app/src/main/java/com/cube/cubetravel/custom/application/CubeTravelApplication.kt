package com.cube.cubetravel.custom.application

import com.ci.v1_ci_view.ui.view.application.CIApplication
import com.cube.cubetravel.manager.AppManager
/** application */
class CubeTravelApplication: CIApplication() {
    companion object{
        lateinit var INSTANCE: CubeTravelApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    val mAppManager by lazy {
        AppManager(this)
    }
}