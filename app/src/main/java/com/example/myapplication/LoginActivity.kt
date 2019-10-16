package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity  : AppCompatActivity(){

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            performLogin()

        }

        textRegistration.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }
//Login
    private fun performLogin(){
        val email = textEmailLogin.text.toString()
        val password = textPasswordLogin.text.toString()

        if (email.isEmpty() || password.isEmpty() ){
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT ).show()
            return
        }

        Log.d("Login", "Attempt login with email/password: $email/***" )

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful)return@addOnCompleteListener
                //else if successful
                Log.d("Main", "Succesfully logged in: ${task.result}")

                val intent = Intent(this, ShopActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Main", "Failed to login: ${it.message}" )
                Toast.makeText(this, "Failed to login", Toast.LENGTH_SHORT ).show()
            }
    }
}