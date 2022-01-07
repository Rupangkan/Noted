package com.repose.noted.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Starred::class], version = 1, exportSchema = false)
abstract class StarredRoomDatabase : RoomDatabase() {
    abstract fun starredDao(): StarredDao
    companion object {
        @Volatile
        private var INSTANCE: StarredRoomDatabase? = null
        fun getDatabase(context: Context): StarredRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StarredRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}