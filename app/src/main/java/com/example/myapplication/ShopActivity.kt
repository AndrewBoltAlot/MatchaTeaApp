package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.drawer_shop.*
import kotlinx.android.synthetic.main.layout_shop.*

class ShopActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_shop)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        isUserLoggedin()

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerlayout.closeDrawers()
            when(it.itemId){
                R.id.menu_shopping_cart -> d("Drawer Menu", "Shopping Cart Was Pressed!")
                R.id.menu_matcha_teas -> d("Drawer Menu", "Matcha Tea Was Pressed!")
                R.id.menu_tea_sets -> d("Drawer Menu", "Tea Sets Was Pressed!")
            }
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }

            val products = arrayListOf<Product>()

        for (i in 0..100){
            products.add(Product("Matcha Tea #$i", "http://via.placeholder.com/350/dddddd/000000",
             20.00))
        }

        matchaTea_recycler_view.apply {
           layoutManager = GridLayoutManager(this@ShopActivity, 2)
            adapter = ProductsAdapter(products = products)

        }



    }

    private fun isUserLoggedin(){
        val uid = FirebaseAuth.getInstance().uid
        if(uid == null){
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.menu_signout ->{
            FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
        }
        }
        drawerlayout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}



