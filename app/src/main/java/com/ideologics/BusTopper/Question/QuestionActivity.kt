package com.ideologics.BusTopper.Question

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ideologics.BusTopper.Login.AccountsActivity
import com.ideologics.BusTopper.R

class QuestionActivity : AppCompatActivity() {



    lateinit var  viewModel : QuestionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)



        val studentBtn: Button = findViewById(R.id.student_btn)
        val driverBtn : Button = findViewById(R.id.driver_btn)
        val usertypeDisplay : TextView = findViewById(R.id.typedisplay)

        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        viewModel.userType.observe(this , Observer {
            if(viewModel.userType.value != ""){
                usertypeDisplay.visibility = View.VISIBLE
                usertypeDisplay.text = viewModel.userType.value .toString()

            }
        })


        studentBtn.setOnClickListener {

            viewModel.userType.value = "Student"
            //navigate()


        }

        driverBtn.setOnClickListener {
            viewModel.userType.value = "Driver"
            navigate()

        }



    }

    fun navigate() {
        startActivity(Intent(this, AccountsActivity::class.java))
    }
}