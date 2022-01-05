package com.ideologics.BusTopper.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ideologics.BusTopper.DashboardActivity
import com.ideologics.BusTopper.R

class Signup : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        val logintxt = view.findViewById<TextView>(R.id.logintxt)
        val btn : Button = view.findViewById(R.id.signup_btn)

        logintxt.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_login)
        }

        btn.setOnClickListener {
            val intent = Intent(this.context , DashboardActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}