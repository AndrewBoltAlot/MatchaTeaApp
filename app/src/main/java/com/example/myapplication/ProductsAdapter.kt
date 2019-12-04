package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import android.content.Context
import android.widget.Toast
import com.example.myapplication.EventBus.CountCartEvent
import org.greenrobot.eventbus.EventBus

class ProductsAdapter(internal var context: Context,
                      private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo_url", products[holder.adapterPosition].photoUrl)
            parent.context.startActivity(intent)
        }
        return holder
     }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(products[position].photoUrl).into(holder.image)
     holder.title.text = products[position].title
        holder.price.text = products[position].price.toString()

        holder.img_cart?.setOnClickListener {
                    Toast.makeText(context, "Add to Cart success", Toast.LENGTH_SHORT).show()
                    //Here we will send notify to Shop Activity to update CounterFab
                    EventBus.getDefault().postSticky(CountCartEvent(true))
        }

    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image: ImageView = itemview.findViewById(R.id.matchaPhoto)
        val title: TextView = itemview.findViewById(R.id.matchaTitle)
        val price: TextView = itemview.findViewById(R.id.matchaPrice)
        val img_cart: ImageView? = itemview.findViewById(R.id.addToCart)

    }
}
