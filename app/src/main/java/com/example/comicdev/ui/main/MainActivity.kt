package com.example.comicdev.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.comicdev.ui.LoadingFragment
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext

@AndroidEntryPoint
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

        //modalLoadingFragment.show(supportFragmentManager, "Loading fragment")

        /*
        Handler().postDelayed({
            if (repository.getUser().) {
                modalLoadingFragment.dismiss()
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("calledFrom", "firstTime")
                startActivity(intent)
            } else modalLoadingFragment.dismiss()
        }, 2000)*/

    }
}