package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 分類1 資料 */
class CategoryBean {
    /** 分類1 id */
    @SerializedName("id")
    var id: Int? = null
    /** 分類1 名稱 */
    @SerializedName("name")
    var name: String? = null
}