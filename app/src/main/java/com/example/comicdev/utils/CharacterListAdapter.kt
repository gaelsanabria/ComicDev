package com.example.comicdev.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comicdev.R
import com.example.comicdev.domain.model.CharacterModel
import com.example.comicdev.ui.Character.CharacterDetailsFragment
import com.example.comicdev.ui.CharactersList.CharactersFragment
import com.example.comicdev.ui.main.MainActivity

class CharacterListAdapter(private val context: Context, var itemList: ArrayList<CharacterModel>) :
    RecyclerView.Adapter<
            CharacterListAdapter.CharacterListViewHolder>() {
    inner class CharacterListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail: ImageView = view.findViewById(R.id.cardview_character_img)
        val cardCharacter: LinearLayout = view.findViewById(R.id.layout_list_item_character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = itemList[position]
        val imageUrl = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExt}"
        val listofImages = listOf<Int>(
            R.drawable.img_characters_test, R.drawable.img_characters_test,
            R.drawable.img_comic_test, R.drawable.img_comic_test, R.drawable.img_comic_test,
            R.drawable.img_comic_test, R.drawable.img_comic_test, R.drawable.img_comic_test
        )
        Glide.with(context).load(imageUrl).placeholder(listofImages[(0..7).random()])
            .into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener {
            val characterDetailsFragment = CharacterDetailsFragment();
            val bundle = Bundle()
            bundle.putInt("id", list.id)
            characterDetailsFragment.arguments = bundle
            val transaction = CharactersFragment().childFragmentManager.beginTransaction()
            transaction.replace(R.id.character_details_fragment, characterDetailsFragment)
            transaction.addToBackStack("BackToCharactersSection")
            transaction?.commit()
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(characterList: ArrayList<CharacterModel>) {
        this.itemList.addAll(characterList)
        notifyDataSetChanged()
    }
}