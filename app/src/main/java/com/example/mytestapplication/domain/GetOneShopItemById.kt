package com.example.mytestapplication.domain

class GetOneShopItemById(private val shopListRepository: ShopListRepository) {

    fun getOneShopItemById(shopItemId: Int) : ShopItem{
       return shopListRepository.getOneShopItemById(shopItemId)
    }
}