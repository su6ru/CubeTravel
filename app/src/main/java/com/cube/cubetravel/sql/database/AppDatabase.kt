package com.cube.cubetravel.sql.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cube.cubetravel.sql.dao.AttractionsCollectionDao
import com.cube.cubetravel.sql.dao.AttractionsCollectionImageUrlDao
import com.cube.cubetravel.sql.table.AttractionsCollectionImageUrlTable
import com.cube.cubetravel.sql.table.AttractionsCollectionTable

@Database(entities = [AttractionsCollectionTable::class,AttractionsCollectionImageUrlTable::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    /** 景點收藏 Dao*/
    abstract fun attractionsCollectionDao(): AttractionsCollectionDao
    /** 景點收藏 圖片Dao*/
    abstract fun attractionsCollectionImageUrlDao(): AttractionsCollectionImageUrlDao
}