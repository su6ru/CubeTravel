package com.cube.cubetravel.custom.application

import com.ci.v1_ci_view.ui.view.application.CIApplication

class CubeTravelApplication: CIApplication() {
    companion object{
        val INSTANCE: CubeTravelApplication by lazy { CubeTravelApplication() }
    }
}