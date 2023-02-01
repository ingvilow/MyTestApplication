package com.example.mytestapplication.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.data.ShopListRepoImpl
import com.example.mytestapplication.domain.DeleteShopItem
import com.example.mytestapplication.domain.EditShopItem
import com.example.mytestapplication.domain.GetShopItemUseCase
import com.example.mytestapplication.domain.ShopItem

class HomeViewModel : ViewModel() {

    private val repository = ShopListRepoImpl

    //we need to pass repository to this use cases, but the true way is using DI, not like this now
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItem(repository)
    private val editShopItem = EditShopItem(repository)

    //mutablelivedata is heir of abstract class LiveData so we can't use pure LiveData and should use mutablelivedata instead
    //after creation mutablelist we can remove func getShopList and call list like this below

    val shopList = getShopItemUseCase.getShopList()


    // delete item and update list
    fun deleteItemInShopList(shopItem: ShopItem){
       deleteShopItemUseCase.deleteShopItem(shopItem)

    }

    fun editItemListShop(shopItem: ShopItem){
        val newValue = shopItem.copy(enabled = !shopItem.enabled)
        editShopItem.editShopItem(newValue)

    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}