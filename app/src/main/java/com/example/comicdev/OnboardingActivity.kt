package com.example.comicdev

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.comicdev.databinding.ActivityOnboardingBinding
import com.example.comicdev.ui.main.ViewPagerAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OnboardingActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val images =
            listOf(R.drawable.img_marvel_logo, R.drawable.img_hulk, R.drawable.img_spiderman)
        val backColors =
            listOf(R.color.darker_grey, R.color.background_green, R.color.background_red)
        val titles = listOf(
            R.string.onboarding_title_1,
            R.string.onboarding_title_2,
            R.string.onboarding_title_3
        )
        val textList = listOf(
            R.string.onboarding_text_1,
            R.string.onboarding_text_2,
            R.string.onboarding_text_3
        )
        val adapter = ViewPagerAdapter(images, backColors, titles, textList)
        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                triggerButtonChanges()
                changeColor()
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

        })
        binding.btnNext.setOnClickListener() {
            val currPos: Int = binding.viewPager.currentItem
            if ((currPos + 1) != binding.viewPager.adapter?.itemCount) {
                binding.viewPager.currentItem = currPos + 1
            } else {
                val intent = Intent(this, LoadingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun triggerButtonChanges() {
        if (binding.viewPager.currentItem < 2) {
            binding.btnNext.setText(R.string.default_next)
        } else {
            binding.btnNext.setText(R.string.finish)
        }
    }

    private fun changeColor() {
        //DataBinding
        when (binding.viewPager.currentItem) {
            0 -> {
                binding.dot1.setBackgroundColor(resources.getColor(R.color.default_red))
                binding.dot2.setBackgroundColor(resources.getColor(R.color.darker_grey))
                binding.dot3.setBackgroundColor(resources.getColor(R.color.darker_grey))
            }
            1 -> {
                binding.dot1.setBackgroundColor(resources.getColor(R.color.darker_grey))
                binding.dot2.setBackgroundColor(resources.getColor(R.color.default_red))
                binding.dot3.setBackgroundColor(resources.getColor(R.color.darker_grey))
            }
            2 -> {
                binding.dot1.setBackgroundColor(resources.getColor(R.color.darker_grey))
                binding.dot2.setBackgroundColor(resources.getColor(R.color.darker_grey))
                binding.dot3.setBackgroundColor(resources.getColor(R.color.default_red))
            }
        }
    }

    private fun showLoadingDialog(){
            //MaterialAlertDialogBuilder(this).setView()
    }

}