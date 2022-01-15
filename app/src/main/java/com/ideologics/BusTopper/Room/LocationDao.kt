package com.ideologics.room.Inventory


import androidx.room.*
import com.ideologics.BusTopper.Login.fragments.BoardingPoint

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item : Location)

    @Update
    suspend fun update(item : Location)

    @Query("SELECT * from location WHERE id = :id")
    fun getBoardingPoint(id : Int) : Location

    @Query("SELECT COUNT(id) FROM location")
    fun getCount() : Int

//    @Query("SELECT * FROM location")
//    fun getAllBoardingPoints() : ArrayList<Location>
}