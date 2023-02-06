package com.example.mytestapplication.presentation.list_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication.R
import com.example.mytestapplication.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val shop_text_items = view.findViewById<TextView>(R.id.text_view_shop)
        val shop_text_items2 = view.findViewById<TextView>(R.id.text_view_shop2)
    }

    var list = listOf<ShopItem>()

    //it is bad desihgn pattern to use notifyDataSetChanged()

    // inflate  is kinda hard operation,
    // especially with a big lists of data if we will use self-written recycler view
    // with Linear Layout it can freeze UI for a long time
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(
           R.layout.frame_textview,
           parent,
           false
       )
        return ShopItemViewHolder(view)
    }

    // add some UI elements in here
    // also all this big stuff was made just to create view element ONLY ONE time
    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = list[position]
        val enabledStatus = if (shopItem.enabled){
            "Active"
        }else{
            "Not active"
        }
        holder.view.setOnLongClickListener {
            true
        }
        if (shopItem.enabled){
            holder.shop_text_items.text = "${shopItem.name} $enabledStatus"
            holder.shop_text_items2.text = shopItem.description
            holder.shop_text_items.setTextColor(
                ContextCompat.getColor(holder.view.context, android.R.color.black))
        }

    }

    // understand by element's position what item is enabled or not
    override fun getItemViewType(position: Int): Int {
        val shops = list[position]
       return if (shops.enabled){
           ENABLED
        }else{
            DISABLED
        }
    }

    // we should use this method because in other case when our view scrolls down all
    // item became active and our check is not working
    override fun onViewRecycled(holder: ShopItemViewHolder) {
        super.onViewRecycled(holder)
        holder.shop_text_items.text = ""
        holder.shop_text_items2.text = ""
        holder.shop_text_items.setTextColor(
            ContextCompat.getColor(holder.view.context, android.R.color.black))
    }
    override fun getItemCount(): Int {
       return list.size
    }

    companion object{
        const val ENABLED: Int = 1
        const val DISABLED: Int = 0
    }
}


