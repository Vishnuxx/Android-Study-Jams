package com.ideologics.BusTopper.DriverActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DriverDashViewModel : ViewModel() {
    val isTracking : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}