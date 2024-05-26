package com.cube.cubetravel.data.repository

import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.beans.WebBean
import com.cube.cubetravel.data.network.ITravelApiService
import com.cube.cubetravel.data.network.call.TravelApiCall
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import retrofit2.http.Path
import retrofit2.http.Query

/** WebActivity相關的  Repository*/
class WebRepository(private val intentWebBean: WebBean?) {
    /** 取得上一個Activity傳到此Activity的傳入值 */
    fun getLastActivityIntentData() = intentWebBean

}