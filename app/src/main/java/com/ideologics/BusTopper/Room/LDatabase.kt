package com.ideologics.BusTopper.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Tables::class] , version = 1 , exportSchema = false)
abstract class LDatabase : RoomDatabase() {
    abstract fun dao() : Dao

    companion object {
        @Volatile
        private var INSTANCE: LDatabase? = null

        fun getDatabase(context: Context): LDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}