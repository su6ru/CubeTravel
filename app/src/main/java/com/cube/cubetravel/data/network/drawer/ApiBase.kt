package com.cube.cubetravel.data.network.drawer

import com.cube.cubetravel.data.beans.AttractionsBean
import com.cube.cubetravel.data.network.data.BaseApiData
import com.google.gson.annotations.SerializedName

class ApiBase {
    /** 景點列表 取得 */
    class GetAttractionsList{
        class Response : BaseApiData.Response<MutableList<AttractionsBean>>(){

        }
    }
}