package com.example.myapplication.repos

import com.example.myapplication.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
     return Single.create<List<Product>> {
        val json = URL("https://my-json-server.typicode.com/AndrewBoltAlot/RestApiMatchaTeaApp/products").readText()
         val products = Gson().fromJson(json, Array<Product>::class.java).toList()
         it.onSuccess(products)
     }

    }
}