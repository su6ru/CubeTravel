package com.cube.cubetravel.sql.dao

import androidx.room.Dao
import androidx.room.Insert
import com.cube.cubetravel.sql.table.AttractionsCollectionTable

/** 景點收藏 DAO */
@Dao
interface AttractionsCollectionDao {
    /** 寫入單一 景點收藏資料 */
    @Insert
    suspend fun insertAttractionsCollection(newsCollection: AttractionsCollectionTable)
}