package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.example.myapplication.EventBus.CountCartEvent
import com.example.myapplication.badge.NotificationCountSetClass
import com.example.myapplication.database.*
import com.google.android.gms.common.internal.service.Common
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.drawer_shop.*
import kotlinx.android.synthetic.main.product_details.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class ShopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_shop)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        doAsync {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "Products"
            ).build()

            db.productDao().InsertAll(ProductDatabase(1, "Encha Matcha",
                "https://images-na.ssl-images-amazon.com/images/I/41l88mMyVTL._SS500_.jpg", 27.95),
                ProductDatabase(2, "Grace Matcha",
                "https://cdn.shopify.com/s/files/1/0796/2801/products/matcha-gold-1-3_grande.jpg?v=1552140980", 25.95),
                ProductDatabase(3, "Soar Organics",
                    "https://www.thebnecessities.com/wp-content/uploads/2019/06/soar-organics-ceremonial-matcha-30g-tin_600x-300x300.jpg", 25.99 ),
                ProductDatabase(4,"Pure Matcha","https://s.s-bol.com/imgbase0/imagebase3/large/FC/3/0/7/1/9200000076261703.jpg", 20.99)
                )
            val products = db.productDao().getAll()

            val cart = db.cartDao()
            cart.InsertAll(CartModel(1, "Encha Matcha", 27.95, 3))

            val allCartItems = cart.getAll()

            uiThread {

                allCartItems.forEach {

                }

            }
        }


        isUserLoggedin()


        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, FragmentShopMain())
            .commit()

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_Home ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FragmentShopMain())
                        .commit()
                }
                R.id.menu_shopping_cart -> d("Drawer Menu", "Shopping Cart Was Pressed!")
                R.id.menu_matcha_teas ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, FragmentMatchaTea())
                        .commit()

                }
                R.id.menu_tea_sets -> d("Drawer Menu", "Tea Sets Was Pressed!")
            }
            it.isChecked = true
            drawerlayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
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
            R.id.shopping_cart ->{
                val intent = Intent(this, CartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        drawerlayout.openDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_sign_out, menu)
        return super.onCreateOptionsMenu(menu)
    }


}



