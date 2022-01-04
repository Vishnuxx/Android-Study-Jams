package com.ideologics.BusTopper.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.databinding.FragmentNotificationBinding

class NotificationFragment : Fragment() {
    lateinit var binding : FragmentNotificationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNotificationBinding.inflate(inflater)
        return binding.root
    }


}