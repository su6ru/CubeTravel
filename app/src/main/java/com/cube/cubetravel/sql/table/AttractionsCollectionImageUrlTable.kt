package com.cube.cubetravel.sql.table

import androidx.room.Entity
import androidx.room.PrimaryKey
/** 景點收藏引用圖片 資料表
 * @param id id
 * @param attractions_item_id 此景點資料於API的原始ID
 * @param src 圖片url
 * */
@Entity(tableName = "attractions_collection_image_url_table")
data class AttractionsCollectionImageUrlTable (
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    , val attractions_item_id: Int?
    , val src: String?
    , val subject: String?
    , val ext: String?
)