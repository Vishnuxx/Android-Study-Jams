package com.ideologics.BusTopper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import com.google.firebase.FirebaseApp
import com.ideologics.BusTopper.Question.QuestionActivity
import com.ideologics.BusTopper.Room.Dao
import com.ideologics.BusTopper.Room.LDatabase
import com.ideologics.BusTopper.Room.Tables
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this.applicationContext
        checkDatabase()

    }

    override fun onRestart() {
        super.onRestart()
        checkDatabase()
    }

    private fun checkDatabase() {
        val dao = LDatabase.getDatabase(this.applicationContext).dao()
        GlobalScope.launch {
          if(isAnEmptyDatabase(dao)){  //check if database is empty
              val table = Tables(
                  0,
                  false,
                  ""
              )
          }else{
             if(dao.getItems(0).isLoggedIn){
                 when(dao.getItems(0).userType) {
                     "Student" -> {

                     }
                     "Driver" -> {
                         navigateTo(DriverDashBoardActivity())
                     }
                 }
             }else{
                 navigateTo(QuestionActivity())
             }
          }
        }
    }

    private fun isAnEmptyDatabase(dao : Dao) : Boolean {
        return dao.getItems(0) == null
    }



    private fun navigateTo(activity: Activity) {
        Timer().schedule(2000) {
            startActivity(Intent(context , activity::class.java))
        }
    }
}