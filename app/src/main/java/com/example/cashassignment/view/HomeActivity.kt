package com.example.cashassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.cashassignment.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = bottomNavigation_home as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home, homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("test", "clicked")
        when(item.itemId){
            R.id.navigation_home ->{
                val homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home, homeFragment).commit()
            }
            R.id.navigation_all_mission ->{
                val homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home, homeFragment).commit()
            }
            R.id.navigation_my_mission ->{
                val homeFragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout_home, homeFragment).commit()
            }
        }
        return true
    }
}