package com.ideologics.BusTopper.StudentActivity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ideologics.BusTopper.ProfileActivity.ProfileActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.RouteActivity.RoutesActivity
import com.ideologics.BusTopper.Utils

class StudentDashboardActivity : AppCompatActivity() {

    private lateinit var fab : CardView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)

        init()
        initLogic()
    }

    fun init() {
        fab = findViewById(R.id.fab)

    }

    fun initLogic() {


        fab.setOnClickListener {
            startActivity(Intent(this.applicationContext , RoutesActivity::class.java))
        }

        val profile : ImageView = findViewById(R.id.profile_icon)
        profile.setOnClickListener{
            startActivity(Intent(this , ProfileActivity::class.java))
        }



    }
}