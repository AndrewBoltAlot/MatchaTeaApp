package com.example.myapplication.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.view.*

class ShoppingCartAdapter(var context: Context, var cartItems: List<CartItem>) :
        RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            p1: Int
        ): ShoppingCartAdapter.ViewHolder {

            // The layout design used for each list item
            val layout =
                LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)

            return ViewHolder(layout)
        }

        // This returns the size of the list.
        override fun getItemCount(): Int = cartItems.size

        override fun onBindViewHolder(viewHolder: ShoppingCartAdapter.ViewHolder, position: Int) {

            //we simply call the `bindItem` function here
            viewHolder.bindItem(cartItems[position])
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            fun bindItem(cartItem: CartItem) {

                // This displays the cart item information for each item
                Picasso.get().load(cartItem.product.photoUrl).into(itemView.cartImage)

                itemView.cartTitle.text = cartItem.product.title

                itemView.cartPrice.text = "$${cartItem.product.price}"

                itemView.cart_quantity.text = cartItem.quantity.toString()

            }
        }

    }
