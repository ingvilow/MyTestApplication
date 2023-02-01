package com.example.mytestapplication.domain

data class ShopItem(
    val name: String,
    val description: String,
    val price: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED,
) {

    //hardcoded values should be in companion objects, otherwise it is "magical number"
    companion object{
        const val UNDEFINED = -1
    }
}