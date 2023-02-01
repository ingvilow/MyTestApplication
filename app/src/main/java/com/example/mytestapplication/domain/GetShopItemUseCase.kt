package com.example.mytestapplication.domain

import androidx.lifecycle.LiveData

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun  getShopList() : LiveData<List<ShopItem>>{
       return shopListRepository.getShopList()
    }

}