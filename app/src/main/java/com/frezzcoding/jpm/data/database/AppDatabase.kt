package com.frezzcoding.jpm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }

}