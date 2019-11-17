package com.example.myapplication

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_shop_main.*
import kotlinx.android.synthetic.main.fragment_shop_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class FragmentShopMain :Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_shop_main, container, false)

        doAsync {
            val json = URL("https://my-json-server.typicode.com/AndrewBoltAlot/RestApiMatchaTeaApp/products").readText()

            uiThread {
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()

                root.matchaTea_recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products = products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }

        val categories = (listOf("Matcha Tea", " Tea Sets", "Japanese Matcha Tea", "American Matcha Tea"))

        root.matchaTea_categories.apply{
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }


        return root
    }
}