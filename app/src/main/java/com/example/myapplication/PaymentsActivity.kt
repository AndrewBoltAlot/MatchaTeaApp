package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PaymentsActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Payments"
    }
}