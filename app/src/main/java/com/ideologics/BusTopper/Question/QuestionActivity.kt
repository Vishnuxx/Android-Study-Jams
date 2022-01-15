package com.ideologics.BusTopper.Question

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ideologics.BusTopper.DriverCodeActivity
import com.ideologics.BusTopper.Login.AccountsActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.Utils
import com.ideologics.room.Inventory.ItemRoomDatabase

class QuestionActivity : AppCompatActivity() {



    lateinit var  viewModel : QuestionViewModel
    lateinit var database: ItemRoomDatabase
    private lateinit var studentBtn: Button
    private lateinit var driverBtn : Button
    private lateinit var usertypeDisplay : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        //database = LDatabase.getDatabase(this.applicationContext)
        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        init()
        initLogic()



    }

    fun init() {
        studentBtn = findViewById(R.id.student_btn)
        driverBtn = findViewById(R.id.driver_btn)
        usertypeDisplay = findViewById(R.id.typedisplay)
    }

    fun initLogic() {

        viewModel.userType.observe(this , Observer {
            if(viewModel.userType.value != ""){
                usertypeDisplay.visibility = View.VISIBLE
                usertypeDisplay.text = viewModel.userType.value .toString()
            }
        })


        studentBtn.setOnClickListener {
            viewModel.userType.value = "Student"
            Utils.putString(this.applicationContext , "userType" , "Student")
            startActivity(Intent(this, DriverCodeActivity::class.java))

        }

        driverBtn.setOnClickListener {
            viewModel.userType.value = "Driver"
            Utils.putString(this.applicationContext , "userType" , "Driver")
            startActivity(Intent(this, AccountsActivity::class.java))

        }

    }



}