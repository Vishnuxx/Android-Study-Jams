package com.ideologics.BusTopper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent : Intent = Intent(this , QuestionActivity::class.java)

        Timer().schedule(2000) {
            startActivity(intent)
        }
    }
}