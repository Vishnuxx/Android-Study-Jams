package com.ideologics.BusTopper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ideologics.BusTopper.DriverActivity.DriverDashBoardActivity
import com.ideologics.BusTopper.Question.QuestionActivity
import com.ideologics.BusTopper.StudentActivity.StudentDashboardActivity
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    lateinit var shared : SharedPreferences
    private lateinit var fAuth : FirebaseAuth
    private lateinit var fReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        context = this.applicationContext
        shared = getSharedPreferences("Test" , MODE_PRIVATE)
        fAuth = FirebaseAuth.getInstance()
        fReference = FirebaseDatabase.getInstance().getReference("/Users/")
        Timer().schedule(2000) {
            checkLogin()
        }


    }

    override fun onRestart() {
        super.onRestart()
        checkLogin()
    }



    private fun checkLogin() {
       

        if(fAuth.currentUser != null) {
            fReference.child(Utils.getString(context , "userType")!!).child(fAuth.uid!!).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot){
                    Log.d("vishnuxxxxxxxxxxx" , dataSnapshot.child("user").value.toString())
                    when(dataSnapshot.child("user").value.toString()) {
                        "Driver" -> {
                            startActivity(Intent(context , DriverDashBoardActivity::class.java))
                        }

                        "Student" -> {
                            startActivity(Intent(context , StudentDashboardActivity::class.java))
                        }

                        else->{
                            Toast.makeText(context , "Something went wrong" , Toast.LENGTH_SHORT)
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context , error.toString() , Toast.LENGTH_LONG)
                }
            })
        }else{
            startActivity(Intent(context , QuestionActivity::class.java))
        }


//            if(Utils.hasAlreadyCreatedAccount(context)) {
//                when (Utils.getString(context, "userMode")) {
//                    "Student" -> {
//
//                    }
//                    "Driver" -> {
//                        startActivity(Intent(context, DriverDashBoardActivity::class.java))
//                    }
//                    else -> {
//                        startActivity(Intent(this@MainActivity, QuestionActivity::class.java))
//
//                    }
//                }
//            }else{
//                //checks if usermode is set even without logging in
//                if(Utils.getString(context , "userMode") != null){
//                    Utils.putString(context , "userMode" , "null");
//                }
//                startActivity(Intent(context , QuestionActivity::class.java))
//            }




    }






}



