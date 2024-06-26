package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 圖片 資料 model */
class ImagesBean {
    /** 圖片 URL */
    @SerializedName("src")
    var src: String? = null
    /** 未知 */
    @SerializedName("subject")
    var subject: String? = null
    /** 副檔名 */
    @SerializedName("ext")
    var ext: String? = null
}