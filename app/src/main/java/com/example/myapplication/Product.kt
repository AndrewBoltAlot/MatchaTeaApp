package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Product(
    var uid: Int?,
    val title: String,
    @SerializedName("photo_url")
    val photoUrl: String,
    val price:Double


)
