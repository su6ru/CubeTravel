package com.cube.cubetravel.data.beans

import com.google.gson.annotations.SerializedName
/** 景點列表資料 model */
class AttractionsBean {
    /** 資料id */
    @SerializedName("id")
    var id: Int? = null
    /** 地點名稱 */
    @SerializedName("name")
    var name: String? = null
    /** 未知 */
    @SerializedName("name_zh")
    var nameZh: String? = null
    /** 未知 */
    @SerializedName("open_status")
    var openStatus: Int? = null
    /** 介紹 */
    @SerializedName("introduction")
    var introduction: String? = null
    /** 開放時間說明 */
    @SerializedName("open_time")
    var openTime: String? = null
    /** 地區 代號 */
    @SerializedName("zipcode")
    var zipcode: String? = null
    /** 地區 (如中正區.萬華區等)*/
    @SerializedName("distric")
    var distric: String? = null
    /** 完整地址 */
    @SerializedName("address")
    var address: String? = null
    /** 電話 */
    @SerializedName("tel")
    var tel: String? = null
    /** 傳真 */
    @SerializedName("fax")
    var fax: String? = null
    /** 信箱 */
    @SerializedName("email")
    var email: String? = null
    /** 未知 */
    @SerializedName("months")
    var months: String? = null
    /** 緯度 */
    @SerializedName("nlat")
    var nlat: Double? = null
    /** 經度 */
    @SerializedName("elong")
    var elong: Double? = null
    /** 未知 */
    @SerializedName("official_site")
    var officialSite: String? = null
    /** facebook */
    @SerializedName("facebook")
    var facebook: String? = null
    /** 未知 */
    @SerializedName("ticket")
    var ticket: String? = null
    /** 疑似位子概述 */
    @SerializedName("remind")
    var remind: String? = null
    /** 未知 */
    @SerializedName("staytime")
    var staytime: String? = null
    /** 疑似資料修改時間 */
    @SerializedName("modified")
    var modified: String? = null
    /** 文章網頁 */
    @SerializedName("url")
    var url: String? = null
    /**分類1*/
    @SerializedName("category")
    var categoryBeanList: MutableList<CategoryBean>? = null
    /**分類2*/
    @SerializedName("target")
    var targetBeanList: MutableList<TargetBean>? = null
    /** 設施服務 */
    @SerializedName("service")
    var serviceBeanList: MutableList<ServiceBean>? = null
    /** 圖片資料 */
    @SerializedName("images")
    var imagesBeanList: MutableList<ImagesBean>? = null





    /** 是否收藏 */
    var isCollection = false
    /** 預計顯示的第一張圖片url取得 */
    var firstImageUrl: String? = null
        get() = imagesBeanList?.firstOrNull()?.src
    // 複製
    fun copy(source: AttractionsBean): AttractionsBean {
        return AttractionsBean().apply {
            id = source.id
            name = source.name
            nameZh = source.nameZh
            openStatus = source.openStatus
            introduction = source.introduction
            openTime = source.openTime
            zipcode = source.zipcode
            distric = source.distric
            address = source.address
            tel = source.tel
            fax = source.fax
            email = source.email
            months = source.months
            nlat = source.nlat
            elong = source.elong
            officialSite = source.officialSite
            facebook = source.facebook
            ticket = source.ticket
            remind = source.remind
            staytime = source.staytime
            modified = source.modified
            url = source.url
            categoryBeanList = source.categoryBeanList?.toMutableList()
            targetBeanList = source.targetBeanList?.toMutableList()
            serviceBeanList = source.serviceBeanList?.toMutableList()
            imagesBeanList = source.imagesBeanList?.toMutableList()
            isCollection = source.isCollection
            firstImageUrl = source.firstImageUrl
        }
    }
}