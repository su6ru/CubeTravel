package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 分類2 資料 */
class TargetBean {
    /** 分類2 id */
    @SerializedName("id")
    var id: Int? = null
    /** 分類2 名稱 */
    @SerializedName("name")
    var name: String? = null
}