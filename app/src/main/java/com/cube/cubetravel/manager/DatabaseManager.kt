package com.cube.cubetravel.manager

import android.content.Context
import androidx.room.Room

object DatabaseManager {

    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase{
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "newsaiDatabase.db")
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            instance
        }
    }

}