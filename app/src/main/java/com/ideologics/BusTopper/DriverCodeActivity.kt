package com.ideologics.BusTopper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.ideologics.BusTopper.Login.AccountsActivity

class DriverCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_code)
        val nextBtn : Button = findViewById(R.id.buttonNxt)
        val editText : TextInputEditText = findViewById(R.id.input)
        val context = this.applicationContext
        nextBtn.setOnClickListener {
            Utils.putString( context!! ,"driverUrl" , editText.text.toString())
            startActivity(Intent(context , AccountsActivity::class.java))
        }

    }
}