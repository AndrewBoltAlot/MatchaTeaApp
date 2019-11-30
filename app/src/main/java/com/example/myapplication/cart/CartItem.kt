package com.example.myapplication.cart

import com.example.myapplication.Product
import com.google.gson.annotations.SerializedName

data class CartItem(
                    var product: Product,
                    var quantity: Int = 0)