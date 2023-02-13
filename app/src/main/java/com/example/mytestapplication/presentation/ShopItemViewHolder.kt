package com.example.mytestapplication.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication.R


    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val shop_text_items = view.findViewById<TextView>(R.id.text_view_shop)
        val shop_text_items2 = view.findViewById<TextView>(R.id.text_view_shop2)
    }
