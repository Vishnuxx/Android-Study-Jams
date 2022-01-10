package com.ideologics.BusTopper.Login.fragments

import android.content.Context
import android.content.Intent
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
import com.google.firebase.database.*
import com.ideologics.BusTopper.DriverActivity.DriverDashBoardActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.StudentActivity.StudentDashboardActivity
import com.ideologics.BusTopper.Utils


class Login : Fragment() {

    private lateinit var viewModel : LoginViewModel
    private  lateinit var rootView: View
    private lateinit var fAuth: FirebaseAuth
    private lateinit var fDB : FirebaseDatabase
    private lateinit var dbReference : DatabaseReference
    private lateinit var mContext : Context



    //UI Views
    private lateinit var loginBtn : Button
    private lateinit var email_inp : EditText
    private lateinit var pass_inp : EditText
    private lateinit var signuptxt : TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel  = ViewModelProvider(this).get(LoginViewModel::class.java)

        mContext = container!!.context

        init()
        initLogic()


        return rootView
    }

    fun init() {
        fAuth = FirebaseAuth.getInstance()
        fDB = FirebaseDatabase.getInstance()
        dbReference  = fDB.getReference("/Users/")

        loginBtn = rootView.findViewById(R.id.login_btn)
        email_inp = rootView.findViewById(R.id.email_txt)
        pass_inp = rootView.findViewById(R.id.pass_txt)
        signuptxt = rootView.findViewById(R.id.signuptxt)

    }



    fun initLogic() {

        loginBtn.setOnClickListener {
            firebaseAuthLogin()
        }

        signuptxt.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_signup)
        }
    }

    fun firebaseAuthLogin() {
        viewModel.email = email_inp.text.toString()
        viewModel.pass = pass_inp.text.toString()

        if (viewModel.isComplete()){
            val login = fAuth.signInWithEmailAndPassword(viewModel.email , viewModel.pass)
            val context = this.context


            login.addOnSuccessListener {
                Toast.makeText(context , "Login successful" , Toast.LENGTH_SHORT).show()
                navigateToDashboard()

            }

            login.addOnFailureListener {
                Toast.makeText(context , it.message.toString() , Toast.LENGTH_SHORT).show()
            }
        }else{

        }
    }


    fun navigateToDashboard() {
        dbReference.child(Utils.getString(mContext ,"userType")!!).child(fAuth.uid!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                //Toast.makeText(context , dataSnapshot.child("user").value.toString() , Toast.LENGTH_SHORT).show()
                when(dataSnapshot.child("user").value.toString()) {
                    "Driver" -> {
                        startActivity(Intent(context , DriverDashBoardActivity::class.java))
                    }

                    "Student" -> {
                        startActivity(Intent(context , StudentDashboardActivity::class.java))
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}