package com.ideologics.BusTopper.Room;

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tables : Tables)

    @Update
    suspend fun update(tables : Tables)

    @Query("SELECT * from local_storage WHERE id = :id")
    fun getItems(id : Int) : Tables
}