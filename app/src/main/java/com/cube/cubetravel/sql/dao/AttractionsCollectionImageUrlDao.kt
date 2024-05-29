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
    /** 透過 attractions_item_id, src, subject, ext 搜尋 圖片資料  */
    @Query("SELECT * FROM attractions_collection_image_url_table WHERE attractions_item_id = :attractionsItemId AND src = :src AND subject = :subject AND ext = :ext LIMIT 1")
    fun getTableByAllParam(attractionsItemId: Int, src: String?, subject: String?, ext: String?): AttractionsCollectionTable?
    /** 根據 attractions_item_id 刪除 全部符合條件之table*/
    @Query("DELETE FROM attractions_collection_image_url_table WHERE attractions_item_id = :attractionsItemId")
    suspend fun deleteDataByAttractionsItemId(attractionsItemId: Int)
}