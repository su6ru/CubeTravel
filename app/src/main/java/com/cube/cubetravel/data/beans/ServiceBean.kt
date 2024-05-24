package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 服務設施 資料 */
class ServiceBean {
    /** 設施 id */
    @SerializedName("id")
    var id: Int? = null
    /** 設施 名稱 */
    @SerializedName("name")
    var name: String? = null
}