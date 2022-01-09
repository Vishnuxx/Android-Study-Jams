package com.ideologics.BusTopper.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ideologics.BusTopper.R
import java.lang.Exception


class fragment_drivercode : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_drivercode, container, false)

        val nextBtn : Button = view.findViewById(R.id.buttonNxt)

        nextBtn.setOnClickListener {
                view.findNavController().navigate(R.id.action_drivercode_to_login2)
        }



        return view
    }

}