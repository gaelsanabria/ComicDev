package com.example.comicdev

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.comicdev.databinding.ActivityOnboardingBinding
import com.example.comicdev.ui.main.ViewPagerAdapter

class OnboardingActivity : AppCompatActivity() {

    private lateinit var dot1:ImageView
    private lateinit var dot2:ImageView
    private lateinit var dot3:ImageView

    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        viewPager2=findViewById(R.id.view_pager)
        dot1=findViewById(R.id.dot_1)
        dot2=findViewById(R.id.dot_2)
        dot3=findViewById(R.id.dot_3)

        val images = listOf(R.drawable.img_marvel_logo, R.drawable.img_hulk, R.drawable.img_spiderman)
        val adapter= ViewPagerAdapter(images)
        viewPager2.adapter = adapter

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

        })
    }

    private fun changeColor() {
        when(viewPager2.currentItem){
            0 ->
            {
                dot1.setBackgroundColor(resources.getColor(R.color.default_red))
                dot2.setBackgroundColor(resources.getColor(R.color.darker_grey))
                dot3.setBackgroundColor(resources.getColor(R.color.darker_grey))
            }
            1 ->
            {
                dot1.setBackgroundColor(resources.getColor(R.color.darker_grey))
                dot2.setBackgroundColor(resources.getColor(R.color.default_red))
                dot3.setBackgroundColor(resources.getColor(R.color.darker_grey))
            }
            2 ->
            {
                dot1.setBackgroundColor(resources.getColor(R.color.darker_grey))
                dot2.setBackgroundColor(resources.getColor(R.color.darker_grey))
                dot3.setBackgroundColor(resources.getColor(R.color.default_red))
            }
        }
    }
}