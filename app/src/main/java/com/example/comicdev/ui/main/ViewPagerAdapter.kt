package com.example.comicdev.ui.main

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.comicdev.R

class ViewPagerAdapter(
    private val images: List<Int>,
    private val backColors: List<Int>,
    private val titles: List<Int>,
    private val texts:List<Int>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.character_img)
        //val cardView: ImageView = itemView.findViewById(R.id.circle_back)
        val titleText: TextView = itemView.findViewById(R.id.section_label) as TextView
        val subText: TextView = itemView.findViewById(R.id.bottom_text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.ViewPagerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_onboarding, parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewPagerViewHolder, position: Int) {
        val currentTitle = titles[position]
        holder.titleText.setText(currentTitle)
        val curBackColor = backColors[position]
        //holder.cardView.setBackgroundColor(curBackColor)
        val curImage = images[position]
        holder.imageView.setImageResource(curImage)
        val currentText = texts[position]
        holder.subText.setText(currentText)
    }

    override fun getItemCount(): Int = images.size
}