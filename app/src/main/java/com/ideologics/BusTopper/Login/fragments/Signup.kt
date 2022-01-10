package com.ideologics.BusTopper.Login.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.Utils

class Signup() : Fragment() {

    //Components
    private lateinit var viewModel : SignUpViewModel
    private lateinit  var rootView : View
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fDB : FirebaseDatabase
    private lateinit var dbReference : DatabaseReference
    private lateinit var mContext : Context

    //UI views
    private lateinit var signupBtn : Button
    private lateinit var logintxt : TextView
    private lateinit var usernameTxt : EditText
    private lateinit var emailTxt : EditText
    private lateinit var passTxt : EditText
    private lateinit var confPassTxt : EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        rootView = inflater.inflate(R.layout.fragment_signup, container, false)

        mContext = container!!.context
        init()
        initLogic()

        return rootView
    }

    fun init() {
        fAuth = FirebaseAuth.getInstance()
        fDB = FirebaseDatabase.getInstance();
        dbReference  = fDB.getReference("/Users/")

        signupBtn = rootView.findViewById(R.id.signup_btn)
        logintxt = rootView.findViewById(R.id.logintxt)
        usernameTxt = rootView.findViewById(R.id.username_txt)
        emailTxt = rootView.findViewById(R.id.email_txt)
        passTxt = rootView.findViewById(R.id.pass_txt)
        confPassTxt = rootView.findViewById(R.id.conf_pass_txt)
    }

    fun initLogic() {

        signupBtn.setOnClickListener {
            viewModel.username = usernameTxt.text.toString()
            viewModel.email = emailTxt.text.toString()
            viewModel.pass = passTxt.text.toString()
            viewModel.confirmedPassword = confPassTxt.text.toString()

            if(viewModel.isComplete()){

                if(viewModel.checkPasswordMatch())
                    if(viewModel.pass.length >= 6) {
                        firebseAuthentication()
                    }else{
                        Toast.makeText(context , "Password must be atleast 6 digits" , Toast.LENGTH_SHORT).show()
                    }

                else
                    Toast.makeText(context , "confirm password does not match" , Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(context , "Please fill all the fields" , Toast.LENGTH_SHORT).show()
            }

        }

        logintxt.setOnClickListener {
            findNavController().navigate(R.id.action_signup_to_login)
        }
    }

    fun firebseAuthentication() {
        val fSignup = fAuth.createUserWithEmailAndPassword(viewModel.email , viewModel.pass)
        val context = this.context

        //on Complete
        fSignup.addOnCompleteListener {
            Toast.makeText(context , "creating..." , Toast.LENGTH_SHORT).show()
            if(it.isSuccessful) {
                Toast.makeText(context , "Success", Toast.LENGTH_SHORT).show()

                createUser()
                findNavController().navigate(R.id.action_signup_to_login)
            }else{
                Toast.makeText(context , "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }

        //on Failure
        fSignup.addOnFailureListener {
            Toast.makeText(context , "Failed to create" , Toast.LENGTH_SHORT).show()
        }

    }

    fun createUser() {
        when(Utils.getString(mContext , "userType")){
            "Student" -> {
                createStudentData()
            }

            "Driver" -> {
                createDriverData()
            }

        }
    }

    fun createDriverData() {
        val map : Map<String , Any> = mapOf<String , Any>(
            "id" to "BusTopper-" + fAuth.uid.toString(),
            "user" to "Driver" ,
            "institution" to "school neme" ,
            "name" to usernameTxt.text.toString(),
            "phone" to "",
            "busNumber" to "0",
            "students" to mapOf<String , String>("Student1" to "LINK1" , "Student2" to "LINK2"),
            "boardingPoints" to mapOf<String , BoardingPoint>(
                "palayam" to BoardingPoint(
                    "palayam",
                    "0.00023",
                    "0.000323",
                    "12:15pm"
                ),
               "karakulam" to BoardingPoint(
                    "karakulam",
                    "0.000223",
                    "0.004240323",
                    "04:40pm"
                )
            ),
            "location" to "location long and lat"
        )

        dbReference.child("Driver").child(fAuth.uid!!).updateChildren(map)
    }


    fun createStudentData() {
        val map : Map<String , Any> = mapOf<String , Any>(
            "id" to "BusTopper-" + fAuth.uid.toString(),
            "user" to "Student" ,
            "institution" to "" ,
            "name" to usernameTxt.text.toString(),
            "guardian" to "",
            "location" to Location(
                "0.0",
                "0.0"
            ),
            "phone" to "",
        "   busNumber" to "0",
        )
        dbReference.child("Student").child(fAuth.uid!!).updateChildren(map)
    }


}

data class BoardingPoint(
    val name : String,
    val latitude : String,
    val longitude : String,
    val time : String
)

data class Location(
    val latitude: String,
    val longitude: String,
    val time : String? = null
)

