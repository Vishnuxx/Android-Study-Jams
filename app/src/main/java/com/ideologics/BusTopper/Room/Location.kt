package com.ideologics.room.Inventory

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(@PrimaryKey(autoGenerate = true) var id : Int = 0,
                @ColumnInfo(name = "name") var name : String,
                @ColumnInfo(name = "location") var latitude : String,
                @ColumnInfo(name = "time") var time : String,
                @ColumnInfo(name = "hasReached") var hasReached : Boolean
)