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
import com.google.android.material.textfield.TextInputEditText
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.Utils
import java.lang.Exception


class fragment_drivercode : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_drivercode, container, false)

        val nextBtn : Button = view.findViewById(R.id.buttonNxt)
        val editText : TextInputEditText = view.findViewById(R.id.input)
        val context = this.context
        nextBtn.setOnClickListener {
                Utils.putString( context!! ,"driverUrl" , editText.text.toString())
                view.findNavController().navigate(R.id.action_fragment_drivercode2_to_signup)
        }



        return view
    }

}