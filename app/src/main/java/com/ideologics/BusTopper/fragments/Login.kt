package com.ideologics.BusTopper.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ideologics.BusTopper.R


class Login : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val signuptxt = view.findViewById<TextView>(R.id.signuptxt)
        signuptxt.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
        return view
    }

}