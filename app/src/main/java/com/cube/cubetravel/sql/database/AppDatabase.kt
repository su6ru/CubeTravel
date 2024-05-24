package com.cube.cubetravel.sql.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import com.cube.cubetravel.sql.table.AttractionsCollectionTable

@Database(entities = [AttractionsCollectionTable::class, AttractionsCollectionTable::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    /** 景點收藏 */
    abstract fun attractionsCollectionDao(): AttractionsCollectionDao

}