package com.cube.cubetravel.data.beans

import com.ci.v1_ci_view.ui.data.provider.IMultipleBannerProvider
/** 圖片Banner專用 model*/
class ImageBannerBean : IMultipleBannerProvider {

    var willUseImageUrl: String = ""

    override fun getImageUrl(): String {
        return willUseImageUrl
    }
}