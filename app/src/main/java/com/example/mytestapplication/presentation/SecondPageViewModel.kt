package com.example.mytestapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapplication.data.ShopListRepoImpl
import com.example.mytestapplication.domain.AddShopItem
import com.example.mytestapplication.domain.EditShopItem
import com.example.mytestapplication.domain.GetOneShopItemById
import com.example.mytestapplication.domain.ShopItem

class SecondPageViewModel : ViewModel() {
    private val repository = ShopListRepoImpl
    private val getOneShopItemById = GetOneShopItemById(repository)
    private val addShopItemUseCase = AddShopItem(repository)
    private val editShopItem = EditShopItem(repository)

    val _errorInputName = MutableLiveData<Boolean>()
    private val _shopItem = MutableLiveData<ShopItem>()

    private val _canClosePage = MutableLiveData<Unit>()

    val canClosePage: LiveData<Unit>
        get() = _canClosePage
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName


    val shopItem: LiveData<ShopItem>
        get() = _shopItem


    fun getShopItem(id: Int) {
        val item = getOneShopItemById.getOneShopItemById(id)
        _shopItem.value = item
    }


    fun addShopItem(inputStringName: String, inputDesc: String?) {
        val name = parsedName(inputStringName)
        val desc = parsedDesc(inputDesc)
        val resultInput = validateInput(inputStringName)
        if (resultInput) {
            val shopItem = ShopItem(name, desc, 0, true)
            addShopItemUseCase.addShopItem(shopItem)
            finishWork()
        }

    }

    fun editShopItem(inputStringName: String, inputDesc: String?) {
        val name = parsedName(inputStringName)
        val desc = parsedDesc(inputDesc)
        val resultInput = validateInput(inputStringName)
        if (resultInput) {
            _shopItem.value?.let {
                val item = it.copy(name = name, description = desc, 0, true)
                editShopItem.editShopItem(item)
                finishWork()
            }


        }
    }

    //remove whitespaces
    private fun parsedName(inputStringName: String?): String {
        return inputStringName?.trim() ?: ""
    }

    private fun parsedDesc(inputDesc: String?): String {
        return inputDesc?.trim() ?: ""
    }

    //check if field is blank
    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    private fun resetError() {
        _errorInputName.value = false
    }

    private fun finishWork() {
        _canClosePage.value = Unit
    }
}