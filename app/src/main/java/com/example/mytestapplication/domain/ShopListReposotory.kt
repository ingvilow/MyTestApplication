package com.example.mytestapplication.domain


// some methods to interface
interface ShopListRepository {
    fun getShopList() : List<ShopItem>
    fun getOneShopItemById(shopItemId: Int) : ShopItem
    fun editShopItem(shopItem: ShopItem)
    fun addShopItem(shopItem: ShopItem)
    fun deleteShopItem(shopItem: ShopItem)
}