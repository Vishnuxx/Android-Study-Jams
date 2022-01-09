package com.ideologics.BusTopper.Question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel : ViewModel() {

    val userType : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}