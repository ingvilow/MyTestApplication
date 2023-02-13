package com.example.mytestapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapplication.R
import com.example.mytestapplication.domain.ShopItem
import com.google.android.material.textfield.TextInputLayout
import java.lang.RuntimeException

class ShopItemActivity : AppCompatActivity() {

    private lateinit var tilName: TextInputLayout
    private lateinit var editName: EditText
    private lateinit var button: Button

    private var  screenMode = MODE_UNKNOW
    private lateinit var viewModelSecond: SecondPageViewModel
    private var shopItemId = ShopItem.UNDEFINED
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout_page)
        parseIntent()

        initViews()
        viewModelSecond = ViewModelProvider(this)[SecondPageViewModel::class.java]
        when(screenMode){
            EDIT_MODE -> laungeEditShopItemMode()
            ADD_MODE -> laungeAddShopItemMode()
        }
        viewModelSecond.errorInputName.observe(this){
            val message =    if(it){
               getString(R.string.error_input)
            }else{
                null
            }
            tilName.error = message
        }


        editName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun laungeEditShopItemMode(){
       viewModelSecond.getShopItem(shopItemId)
        viewModelSecond.shopItem.observe(this){
            editName.setText(it.name)
        }
        button.setOnClickListener(){
           viewModelSecond.editShopItem(editName.text.toString(), editName.text.toString())
        }
    }

    private fun laungeAddShopItemMode(){
        button.setOnClickListener(){
            viewModelSecond.addShopItem(editName.text.toString(), editName.text.toString())
        }
    }
    fun initViews() {
        tilName = findViewById(R.id.outlinedTextField2)
        editName = findViewById(R.id.inputid)
        button = findViewById(R.id.save_button)
    }

    fun parseIntent() {
        if (!intent.hasExtra(SCREEN_MODE)) {
            RuntimeException("no such mode")
        }
        val mode = intent.getStringExtra(SCREEN_MODE)
        if(mode != EDIT_MODE && mode != ADD_MODE){
            throw RuntimeException("Unknown mode")
        }
        screenMode = mode
        if(screenMode == EDIT_MODE && !intent.hasExtra(EXTRA_SHOP_ITEM_ID)){
            RuntimeException("No id found")
        }
        if(screenMode == EDIT_MODE){
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, -1)
        }
    }

    companion object {
        private const val SCREEN_MODE = "extra_mod"
        private const val EDIT_MODE = "edit_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_id"
        private const val ADD_MODE = "edit_add"
        private const val MODE_UNKNOW = ""


        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE, ADD_MODE)
            return intent
        }

        fun newIntentEditItem(context: Context, id: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(SCREEN_MODE, EDIT_MODE)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, id)
            return intent
        }
    }
}