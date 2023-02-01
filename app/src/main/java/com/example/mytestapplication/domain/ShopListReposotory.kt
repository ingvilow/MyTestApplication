package com.example.mytestapplication.domain

import androidx.lifecycle.LiveData


// some methods to interface
interface ShopListRepository {
    fun getShopList() : LiveData<List<ShopItem>>
    fun getOneShopItemById(shopItemId: Int) : ShopItem
    fun editShopItem(shopItem: ShopItem)
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
}