package com.example.mytestapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapplication.R
import com.example.mytestapplication.domain.ShopItem

class ShopItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout_page)
        val mod = intent.getStringExtra(SCREEN_MODE)
        Log.d("SecondActivity", mod.toString())
    }

    companion object{
       private const val SCREEN_MODE = "extra_mod"
       private const val EDIT_MODE = "edit_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_id"
       private const val ADD_MODE = "edit_add"

        fun newIntentAddItem(context: Context) : Intent{
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE, ADD_MODE)
            return  intent
        }
        fun newIntentEditItem(context: Context, id: Int) : Intent{
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE,EDIT_MODE)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return  intent
        }
    }
}