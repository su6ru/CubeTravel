package com.cube.cubetravel.data.network.drawer

import com.cube.cubetravel.data.beans.AttractionsBean
import com.google.gson.annotations.SerializedName

class ApiBase {
    /** 景點取得 */
    class GetAttractionsList{
        class Response{
            @SerializedName("total")
            var total: Int? = 0
            @SerializedName("data")
            var data: MutableList<AttractionsBean>? = mutableListOf()
        }
    }
}