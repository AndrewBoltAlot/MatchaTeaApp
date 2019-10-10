package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.layout_shop.*

class ShopActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_shop)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val products = arrayListOf<Product>()

        for (i in 0..100){
            products.add(Product("MatchaTea","https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRPzKQIGlrhQQ1b3qPsk48gogQY9P-txi5C8lrH0yaIH46qgjdHA1V9lYCBg1CyxTJWbTpHjpiIxnSBsbJC2MQxi1CVpXihBN3mrc_93rjS1Xi_n-Uj64Lsbg&usqp=CAc"
            , 20.00))
        }

        matchaTea_recycler_view.apply {
            layoutManager = GridLayoutManager(this@ShopActivity, 2)
            adapter = ProductsAdapter(products)

        }
    }
}