package com.ideologics.BusTopper.Login.fragments

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
import com.ideologics.BusTopper.Login.SignUpViewModel
import com.ideologics.BusTopper.R

class Signup() : Fragment() {

    //Components
    private lateinit var viewModel : SignUpViewModel
    private lateinit  var rootView : View
    private lateinit var fAuth: FirebaseAuth

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


        init()
        initLogic()

        return rootView
    }

    fun init() {
        fAuth = FirebaseAuth.getInstance()

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
                findNavController().navigate(R.id.action_signup_to_login)
            }else{
                Toast.makeText(context , "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        //on Failure
        fSignup.addOnFailureListener {
            Toast.makeText(context , "Failed to create" , Toast.LENGTH_SHORT).show()
        }

    }

}

