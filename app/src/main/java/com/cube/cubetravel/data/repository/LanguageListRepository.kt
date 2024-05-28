package com.cube.cubetravel.data.repository

import android.content.Context
import com.ci.v1_ci_view.ui.listener.IOnOptionListener
import com.ci.v1_ci_view.ui.util.CIResourceUtil
import com.cube.cubetravel.R
import com.cube.cubetravel.custom.application.CubeTravelApplication
import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.beans.LanguageBean
import com.cube.cubetravel.data.beans.NewsBean
import com.cube.cubetravel.data.network.ITravelApiService
import com.cube.cubetravel.data.network.call.TravelApiCall
import com.cube.cubetravel.data.network.drawer.ApiBase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import com.google.gson.Gson
import retrofit2.http.Path
import retrofit2.http.Query

/** LanguageListActivity相關的  Repository*/
class LanguageListRepository(private val context: Context) {
    /** 從raw 取的 語言列表 */
    fun getLanguageBeanList(): List<LanguageBean>{

        val rawString = CIResourceUtil.byRawResource(context, R.raw.language)

        return Gson().fromJson(rawString, Array<LanguageBean>::class.java).toList()

    }

}