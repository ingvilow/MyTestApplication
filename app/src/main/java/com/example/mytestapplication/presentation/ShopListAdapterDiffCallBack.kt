package com.example.mytestapplication.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.mytestapplication.domain.ShopItem


//diffutil is required to compare two lists (it can be the one same list but items in it should be compared)
// very useful when it comes to use Adapters
class ShopListAdapterDiffCallBack(var oldList: List<ShopItem>, var newList: List<ShopItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
      return  oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    // compare by id
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return  oldItem.id == newItem.id
        //my implementation below
       /* return oldList[oldItemPosition].id == oldList[newItemPosition].id*/
    }

    //if fields object are the same
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return  oldItem == newItem
    }
}