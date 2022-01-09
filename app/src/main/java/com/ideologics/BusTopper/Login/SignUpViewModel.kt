package com.ideologics.BusTopper.Login

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    //Data
     lateinit var username : String
     lateinit var email : String
     lateinit var pass : String
     lateinit var confirmedPassword : String
     private lateinit var isStudent : String

    //Constants
    private val USER_TYPE_STUDENT = 1
    private val USER_TYPE_DRIVER = 2


    fun isComplete() : Boolean {
        return !username.isBlank() &&  !email.isBlank() && !pass.isBlank() && checkPasswordMatch()
    }

    fun checkPasswordMatch() : Boolean {
        return pass == confirmedPassword
    }



    fun getUserType() : String{
        return ""
    }

}