package com.cube.cubetravel.sql.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cube.cubetravel.sql.table.AttractionsCollectionImageUrlTable
import com.cube.cubetravel.sql.table.AttractionsCollectionTable
import kotlinx.coroutines.flow.Flow

/** 景點收藏的圖片 DAO */
@Dao
interface AttractionsCollectionImageUrlDao {
    /** 寫入 array 圖片資料 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTableList(attractionsCollectionImageUrlTableList: List<AttractionsCollectionImageUrlTable>)
    /** 根據attractions_item_id 取得 圖片資料列表 */
    @Query("SELECT * FROM attractions_collection_image_url_table WHERE attractions_item_id = :attractionsItemId ORDER BY id ASC")
    fun getTableListByAttractionsItemId(attractionsItemId: Int): Flow<List<AttractionsCollectionImageUrlTable>>

    /** 根據 attractions_item_id 刪除 全部符合條件之table*/
    @Query("DELETE FROM attractions_collection_image_url_table WHERE attractions_item_id = :attractionsItemId")
    suspend fun deleteDataByAttractionsItemId(attractionsItemId: Int)
}