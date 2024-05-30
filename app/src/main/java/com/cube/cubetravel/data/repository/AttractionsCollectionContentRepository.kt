package com.cube.cubetravel.data.repository

import com.cube.cubetravel.data.beans.AttractionsCollectionBean
import com.cube.cubetravel.data.beans.ImageBannerBean

/** AttractionsCollectionContentActivity相關的  Repository*/
class AttractionsCollectionContentRepository(private val intentAttractionsCollectionBean: AttractionsCollectionBean?) {
    /** 取得上一個Activity傳到此Activity的傳入值 */
    fun getLastActivityIntentData() = intentAttractionsCollectionBean

    /** 將AttractionsCollectionBean的圖片資料格式轉為 Banner 可以使用的資料格式 */
    fun getImageBannerBeanList(): MutableList<ImageBannerBean> {
        val imageBannerBeanList = mutableListOf<ImageBannerBean>()

        val imagesBeanList = intentAttractionsCollectionBean?.imagesBeanList
        if (!imagesBeanList.isNullOrEmpty()){
            for (imagesBean in imagesBeanList) {
                val src = imagesBean.src
                if (!src.isNullOrEmpty()){
                    val imageBannerBean = ImageBannerBean()
                    imageBannerBean.willUseImageUrl = src
                    imageBannerBeanList.add(imageBannerBean)
                }

            }
        }

        return imageBannerBeanList
    }
}
