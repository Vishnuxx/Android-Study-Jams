package com.ideologics.BusTopper.Login.fragments

import android.content.Intent
import android.os.Binder
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
import com.ideologics.BusTopper.Login.LoginViewModel
import com.ideologics.BusTopper.MainActivity
import com.ideologics.BusTopper.R
import com.ideologics.BusTopper.databinding.FragmentLoginBinding
import com.ideologics.BusTopper.databinding.FragmentSignupBinding
import org.w3c.dom.Text


class Login : Fragment() {

    private lateinit var viewModel : LoginViewModel
    private  lateinit var rootView: View
    private lateinit var fAuth: FirebaseAuth



    //UI Views
    private lateinit var loginBtn : Button
    private lateinit var email_inp : EditText
    private lateinit var pass_inp : EditText
    private lateinit var signuptxt : TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel  = ViewModelProvider(this).get(LoginViewModel::class.java)


        init()
        initLogic()


        return rootView
    }

    fun init() {
        fAuth = FirebaseAuth.getInstance()

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

            login.addOnCompleteListener {
                if(it.isSuccessful) {
                    Toast.makeText(context , "Login Success" , Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context , "Login Failed" , Toast.LENGTH_SHORT).show()

                }
            }

            login.addOnSuccessListener {
                Toast.makeText(context , "Login successful" , Toast.LENGTH_SHORT).show()
                startActivity(Intent(context , MainActivity::class.java))
            }

            login.addOnFailureListener {
                Toast.makeText(context , it.message.toString() , Toast.LENGTH_SHORT).show()
            }
        }else{

        }

    }
}