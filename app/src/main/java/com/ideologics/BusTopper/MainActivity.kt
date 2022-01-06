package com.ideologics.BusTopper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ideologics.BusTopper.Question.QuestionActivity
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigate()

    }

    override fun onRestart() {
        super.onResume()
        navigate()
    }

    private fun navigate() {
        val intent = Intent(this, QuestionActivity::class.java)

        Timer().schedule(2000) {
            startActivity(intent)
        }
    }
}