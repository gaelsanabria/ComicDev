package com.example.comicdev

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.comicdev.ui.main.MainActivity
import com.example.comicdev.ui.profile.ProfileActivity
import com.example.comicdev.ui.profile.ProfileViewModel
import kotlinx.coroutines.delay

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        val sharedPreferences = getSharedPreferences("Onboarding", Context.MODE_PRIVATE)
        val isOnboardingShow = sharedPreferences.getString("onboarding", "")

        Handler().postDelayed({
            Log.d("TAG", "onCreate: $isOnboardingShow")
            if (isOnboardingShow == "1") {
                viewModel.getUser.observe(this){ user ->
                    if(user == null){
                        navigateToProfileActivity()
                    }
                    else{
                        navigateToMainActivity()
                    }
                }
            } else {
                navigateToOnboarding()
            }
            finish()
        }, 1000)
    }

    private fun navigateToProfileActivity() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun navigateToOnboarding() {
        startActivity(Intent(this, OnboardingActivity::class.java))
    }
}