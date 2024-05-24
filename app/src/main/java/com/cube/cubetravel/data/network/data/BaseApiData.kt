package com.cube.cubetravel.data.network.data

import com.cube.cubetravel.data.beans.AttractionsBean
import com.google.gson.annotations.SerializedName

class BaseApiData {
    class Request {

    }
    open class Response<T> {
        @SerializedName("total")
        var total: Int? = null
        @SerializedName("data")
        var data: T? = null
    }
}