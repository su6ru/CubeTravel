package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 最新消息列表用的資料 */
class NewsBean {

    /** 資料id */
    @SerializedName("id")
    var id: Int? = null
    /** 標題 */
    @SerializedName("title")
    var title: String? = null
    /** 內文 */
    @SerializedName("description")
    var description: String? = null
    /** 未知 */
    @SerializedName("posted")
    var posted: String? = null
    /** 未知 */
    @SerializedName("modified")
    var modified: String? = null
    /** 內文網址 */
    @SerializedName("url")
    var url: String? = null
    /** 其他連結 */
    @SerializedName("links")
    var linksBeanList: MutableList<LinksBean>? = null


}