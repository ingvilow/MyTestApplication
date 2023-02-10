package com.example.mytestapplication.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication.R
import com.example.mytestapplication.databinding.ActivityMainBinding
import com.example.mytestapplication.domain.ShopItem
import com.example.mytestapplication.presentation.list_adapter.ShopListAdapter
import com.example.mytestapplication.presentation.ui.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : HomeViewModel
    private lateinit var adapter: ShopListAdapter
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.layout.second_lpage_layout
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setupRVShopItem()
        //bind live data from view model to main activity
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.shopList.observe(this){
          adapter.submitList(it)

        }


    }

    private fun setupRVShopItem(){
        val rvShopList = findViewById<RecyclerView>(R.id.recyclerview)
        with(rvShopList.adapter){
            adapter = ShopListAdapter()
            rvShopList.adapter = adapter
            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.ENABLED,
                ShopListAdapter.POOL_VIEW)
            rvShopList.recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.DISABLED,
                ShopListAdapter.POOL_VIEW)
        }

        //it is more java style rather than kotlin
        //in kotlin we should fo smth like this: adapter.editItem = { Log.d("Main Activity",it.toString())}
        adapter.changeListStateR = object : ShopListAdapter.changeListState {
            override fun onClickChangeColorItem(shopItem: ShopItem) {
                viewModel.changeEnableState(shopItem)
            }

            override fun editItem(shopItem: ShopItem) {
               viewModel.editItemListShop(shopItem)
            }

        }
        val swipeHandler = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItemInShopList(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }
    }
