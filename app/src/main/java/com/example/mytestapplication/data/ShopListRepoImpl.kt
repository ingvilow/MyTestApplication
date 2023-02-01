package com.example.mytestapplication.data

import com.example.mytestapplication.domain.ShopItem
import com.example.mytestapplication.domain.ShopListRepository
import java.lang.RuntimeException

//if i am going to add something in added DB's here I can write it
object ShopListRepoImpl : ShopListRepository {

    //create variable which will be collection and here we can keep all the stuff
    private val shopList = mutableListOf<ShopItem>()

    //initialize items to fullfill ShopItem for testing purpose
    init {
        for (i in 0 until 20){
            val listItem = ShopItem("Name $i", "Description $i", i, true)
            addShopItem(listItem)
        }
    }
    //create variable with auto increment id due to the next element can have id automatically when we added it in list
    private var autoIncrementId = 0
    override fun getShopList(): List<ShopItem> {
        //it returns copy of shoplist because it is not a good practice to return the original list
       return  shopList.toList()
    }

    override fun getOneShopItemById(shopItemId: Int): ShopItem {
        //if it is required to be null, we can ShopItem object make null, otherwise it throws an error if element is null
        return  shopList.find { it.id == shopItemId } ?: throw RuntimeException("Element is not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldId = getOneShopItemById(shopItem.id)
        shopList.remove(oldId)
        addShopItem(shopItem)
    }

    override fun addShopItem(shopItem: ShopItem) {
       shopItem.id = autoIncrementId++
       shopList.add(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
       shopList.remove(shopItem)
    }
}