package com.ideologics.BusTopper.DriverActivity

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ideologics.BusTopper.MapsActivity
import com.ideologics.BusTopper.ProfileActivity.ProfileActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.RouteActivity.RoutesActivity
import com.ideologics.BusTopper.databinding.ActivityDriverDashBoardBinding

class DriverDashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDriverDashBoardBinding
    private lateinit var viewModel: DriverDashViewModel

    private lateinit var trackbutton : RelativeLayout
    private lateinit var startTrack : TextView
    private lateinit var buttonImg : ImageView
    private lateinit var profilebtn : ImageView

    private  var isTracking = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_dash_board)
        binding = ActivityDriverDashBoardBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(DriverDashViewModel::class.java)
        init()
        initLogic()
    }

    private fun init() {
        trackbutton  = findViewById(R.id.trackButton)
        startTrack = findViewById(R.id.startTrackingText)
        buttonImg = findViewById(R.id.buttonbgimg)
        profilebtn = findViewById(R.id.profile_icon)
    }

    private fun initLogic() {

        trackbutton.setOnClickListener {
            isTracking = !isTracking
            if(isTracking){
                buttonImg.setBackgroundResource(R.drawable.driver_btn_bg_unselect)
                startTrack.text = "STOP"
                startActivity(Intent(this , RoutesActivity::class.java))
            }else{
                buttonImg.setBackgroundResource(R.drawable.driver_btn_bg_selected)
                startTrack.text = "START"
                startActivity(Intent(this , RoutesActivity::class.java))
            }
        }

        profilebtn.setOnClickListener{
             startActivity(Intent(this , ProfileActivity::class.java))
        }
    }



}