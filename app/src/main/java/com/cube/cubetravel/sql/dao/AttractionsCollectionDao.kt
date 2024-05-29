package com.cube.cubetravel.sql.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cube.cubetravel.sql.table.AttractionsCollectionTable
import kotlinx.coroutines.flow.Flow

/** 景點收藏 DAO */
@Dao
interface AttractionsCollectionDao {
    /** 寫入單一 景點收藏 */
    @Insert
    suspend fun insert(newsCollection: AttractionsCollectionTable)

    /** 取得 全部的 景點收藏*/
    @Query("SELECT * FROM attractions_collection_table ORDER BY id DESC")
    fun getAllTableList(): Flow<List<AttractionsCollectionTable>>
    /** 透過 景點資料id(AttractionsBean的id) 搜尋 景點收藏  */
    @Query("SELECT *  FROM attractions_collection_table WHERE attractions_item_id = :attractionsItemId LIMIT 1")
    fun getTableById(attractionsItemId: Int?): AttractionsCollectionTable?
    /** 刪除 景點收藏 */
    @Delete
    suspend fun deleteTable(attractionsCollectionTable: AttractionsCollectionTable)
}