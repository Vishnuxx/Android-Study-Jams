package com.ideologics.BusTopper.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_storage")
data class Tables(@PrimaryKey(autoGenerate = true) val id : Int = 0,
                  var isLoggedIn : Boolean,
                  var userType : String) {

}
