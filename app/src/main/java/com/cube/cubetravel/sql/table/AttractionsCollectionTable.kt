package com.cube.cubetravel.sql.table

import androidx.room.Entity
import androidx.room.PrimaryKey
/** 景點收藏 資料表*/
@Entity(tableName = "attractions_collection_table")
data class AttractionsCollectionTable (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

)