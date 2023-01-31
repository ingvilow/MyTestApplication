package com.example.mytestapplication.domain

class EditShopItem(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){
        //go to the next page using id (not the whole object) when try to edit shop item
        shopListRepository.editShopItem(shopItem)
    }

}