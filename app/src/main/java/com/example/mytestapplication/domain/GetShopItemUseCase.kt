package com.example.mytestapplication.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun  getShopList() : List<ShopItem>{
       return shopListRepository.getShopList()
    }

}