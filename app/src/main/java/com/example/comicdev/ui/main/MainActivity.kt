package com.example.comicdev.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.comicdev.OnboardingActivity
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityMainBinding
import com.example.comicdev.ui.profile.ProfileActivity
import androidx.appcompat.widget.Toolbar;

class MainActivity : AppCompatActivity() {

    private var profile = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_comics,
                R.id.navigation_characters,
                R.id.navigation_favorites,
                R.id.navigation_profile
            )
        )

        navView.setupWithNavController(navController)

        if (profile == null){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}