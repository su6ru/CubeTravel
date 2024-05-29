package com.cube.cubetravel.sql.table

import androidx.room.Entity
import androidx.room.PrimaryKey
/** 景點收藏 資料表
 * @param id id
 * @param attractions_item_id 此景點資料於API的原始ID
 * @param name 地點名稱
 * @param introduction 地點內容說明
 * @param openTime 開放時間說明
 * @param address 地址
 * @param url 原始網頁
 * */
@Entity(tableName = "attractions_collection_table")
data class AttractionsCollectionTable (
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    , val attractions_item_id: Int?
    , val name: String?
    , val introduction: String?
    , val openTime: String?
    , val address: String?
    , val url: String?
)