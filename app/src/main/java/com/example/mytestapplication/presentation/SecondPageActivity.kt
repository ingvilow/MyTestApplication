package com.example.mytestapplication.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapplication.R

class SecondPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.second_lpage_layout)
        val mode = intent.getStringExtra("modd")
        Log.d("shopItem", mode.toString())
    }
}