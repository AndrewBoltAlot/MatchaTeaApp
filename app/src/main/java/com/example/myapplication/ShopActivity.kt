package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.layout_shop.*

class ShopActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_shop)
        setSupportActionBar(findViewById(R.id.my_toolbar))

       isUserLoggedin()

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(ShopItem())
        adapter.add(ShopItem())
        adapter.add(ShopItem())

        matchaTea_recycler_view.adapter = adapter

    /*    val products = arrayListOf<Product>()

        for (i in 0..100){
            products.add(Product("MatchaTea","https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRPzKQIGlrhQQ1b3qPsk48gogQY9P-txi5C8lrH0yaIH46qgjdHA1V9lYCBg1CyxTJWbTpHjpiIxnSBsbJC2MQxi1CVpXihBN3mrc_93rjS1Xi_n-Uj64Lsbg&usqp=CAc"
            , 20.00))
        }

        matchaTea_recycler_view.apply {
            layoutManager = GridLayoutManager(this@ShopActivity, 2)
            adapter = ProductsAdapter(products = products)

        }*/
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

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

class ShopItem: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        //will be called in our list for each shop object later on..
    }

    override fun getLayout(): Int {
        return R.layout.product_row
    }
}

