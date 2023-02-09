package com.example.mytestapplication.presentation.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.mytestapplication.R
import com.example.mytestapplication.domain.ShopItem
import com.example.mytestapplication.presentation.ShopItemViewHolder
import com.example.mytestapplication.presentation.ShopListAdapterDiffCallBack

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopListAdapterDiffCallBack()) {


    //it can be changed to lambda expression
    var changeListStateR: changeListState? = null


    // inflate  is kinda hard operation,
    // especially with a big lists of data if we will use self-written recycler view
    // with Linear Layout it can freeze UI for a long time
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layot = when(viewType){
            ENABLED -> R.layout.frame_textview
            DISABLED -> R.layout.frame_textview_disabled
            else -> throw RuntimeException("Unkown")
        }
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
        val shopItem = getItem(position)
        onLongClick(holder, shopItem)
        onFastClick(holder, shopItem)
        holder.shop_text_items.text = shopItem.name
        holder.shop_text_items2.text = shopItem.description
    }


    //TODO: smth wrong here I need to check
    private fun onFastClick(
        holder: ShopItemViewHolder,
        shopItem: ShopItem
    ) {
        holder.view.setOnClickListener {
            changeListStateR?.editItem(shopItem)
            true
        }
    }

    private fun onLongClick(
        holder: ShopItemViewHolder,
        shopItem: ShopItem
    ) {
        holder.view.setOnLongClickListener {
            changeListStateR?.onClickChangeColorItem(shopItem)
            true
        }
    }

    // understand by element's position what item is enabled or not
    override fun getItemViewType(position: Int): Int {
        val shops = getItem(position)
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


    //I CAN CREATE INSIDE RECYCLERVIEW INTERFACE???? WHF IS MAGIC?????
    interface changeListState{
        fun onClickChangeColorItem(shopItem: ShopItem)
        fun editItem(shopItem: ShopItem)
    }

    companion object{
        const val ENABLED: Int = 1
        const val DISABLED: Int = 0


        //check pools of created view holder and allow do not inflate new item
        const val POOL_VIEW: Int = 10
    }
}


