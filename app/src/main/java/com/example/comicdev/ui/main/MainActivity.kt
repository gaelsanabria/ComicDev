package com.example.comicdev.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.comicdev.LoadingFragment
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityMainBinding
import com.example.comicdev.db.AppDatabase
import com.example.comicdev.db.repositories.UserRepository
import com.example.comicdev.ui.profile.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val modalLoadingFragment = LoadingFragment()

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

        modalLoadingFragment.show(supportFragmentManager, "Loading fragment")

        val repository: UserRepository
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        Handler().postDelayed({
            if (repository.loadUserData().isEmpty()) {
                modalLoadingFragment.dismiss()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else modalLoadingFragment.dismiss()
        }, 2000)

    }
}