package com.example.mytestapplication.presentation

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapplication.R
import com.example.mytestapplication.databinding.ActivityMainBinding
import com.example.mytestapplication.presentation.list_adapter.ShopListAdapter
import com.example.mytestapplication.presentation.ui.home.HomeViewModel

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
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        setupRVShopItem()
        //bind live data from view model to main activity
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.shopList.observe(this){
            Log.d("Here we go", it.toString())
          adapter.list = it

        }


    }

    private fun setupRVShopItem(){
        val rvShopList = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = ShopListAdapter()
        rvShopList.adapter = adapter
    }
}