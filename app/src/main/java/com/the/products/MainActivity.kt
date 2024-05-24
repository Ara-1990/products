package com.the.products

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.the.products.databinding.ActivityMainBinding
import com.the.products.ui.mainfrag.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var fragment = MainFragment()
        val fragmentManager = supportFragmentManager
        val fragmnetTransaction= fragmentManager.beginTransaction()
        fragmnetTransaction.replace(R.id.fragment_activity_main,fragment)
        fragmnetTransaction.commit()


    }
}