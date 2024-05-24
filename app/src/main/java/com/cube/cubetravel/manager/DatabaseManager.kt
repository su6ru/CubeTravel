package com.cube.cubetravel.manager

import android.content.Context
import androidx.room.Room
import com.cube.cubetravel.sql.database.AppDatabase

object DatabaseManager {

    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "CubeTravelDatabase.db")
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }

}