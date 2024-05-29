package com.cube.cubetravel.data.beans

/** 景點收藏 model */
class AttractionsCollectionBean {
    /** 資料表流水號 id */
    var id: Int? = null
    /** 與 api真實資料對應的 資料id (等同AttractionsBean.id) */
    var attractionsItemId: Int? = null
    /** 地點名稱 */
    var name: String? = null
    /** 介紹 */
    var introduction: String? = null
    /** 開放時間說明 */
    var openTime: String? = null
    /** 完整地址 */
    var address: String? = null
    /** 文章網頁 */
    var url: String? = null
    /** 圖片資料 */
    var imagesBeanList: MutableList<ImagesBean>? = null
}