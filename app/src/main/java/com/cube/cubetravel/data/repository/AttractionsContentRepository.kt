package com.cube.cubetravel.data.repository

import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.ImageBannerBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.network.ITravelApiService
import com.cube.cubetravel.data.network.call.TravelApiCall
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import retrofit2.http.Path
import retrofit2.http.Query

/** AttractionsContentActivity相關的  Repository*/
class AttractionsContentRepository(private val intentAttractionsBean: AttractionsBean?) {
    /** 取得上一個Activity傳到此Activity的傳入值 */
    fun getLastActivityIntentData() = intentAttractionsBean

    /** 將AttractionsBean的圖片資料格式轉為 Banner 可以使用的資料格式 */
    fun getImageBannerBeanList(): MutableList<ImageBannerBean> {
        val imageBannerBeanList = mutableListOf<ImageBannerBean>()

        val imagesBeanList = intentAttractionsBean?.imagesBeanList
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
