package com.brazhnik.fobres.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.brazhnik.fobres.R
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navView = findViewById<NavigationView>(R.id.navigationView)
        findViewById<ImageView>(R.id.imageMenu).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(GravityCompat.START)
        }
        navView.itemIconTintList = null
        val navController: NavController =
            Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navView, navController)

        /*val btnProfile: Button = findViewById(R.id.button)
        val btnRating: Button = findViewById(R.id.button2)*/

        /*btnProfile.setOnClickListener {
            //startActivity(Intent(this, ProfileActivity::class.java))
        }
        btnRating.setOnClickListener {
            //startActivity(Intent(this, RatingActivity::class.java))
        }*/
    }
}