package com.example.comicdev.ui.main

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.comicdev.R

class ViewPagerAdapter(private val images:List<Int>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView =itemView.findViewById(R.id.character_img)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_onboarding,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val curImage=images[position]
        holder.imageView.setImageResource(curImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}