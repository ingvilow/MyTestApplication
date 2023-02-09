package com.example.mytestapplication.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.mytestapplication.domain.ShopItem


//diffutil is required to compare two lists (it can be the one same list but items in it should be compared)
// very useful when it comes to use Adapters
class ShopListAdapterDiffCallBack() : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
       return  oldItem == newItem
    }
}