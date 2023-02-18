package com.example.comicdev.ui.Character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.comicdev.R
import com.example.comicdev.databinding.FragmentCharacterDetailsBinding
import com.example.comicdev.databinding.FragmentCharactersBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val viewModelCharacter : CharacterViewModel by viewModels()
    private var _binding: FragmentCharacterDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var idd = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        idd = (arguments?.getInt("id") ?: viewModelCharacter.getCharacterByIdValue(id.toString())) as Int
        CoroutineScope(Dispatchers.Main).launch {
            viewModelCharacter._characterValue.collect {
                when{
                    it.error.isNotBlank() ->{
                        Toast.makeText(context,"Unexpected Error", Toast.LENGTH_LONG).show()
                    }
                    it.characterDetail.isNotEmpty()->{
                        it.characterDetail.map { character ->
                            val url = "${character.thumbnail}/landscape_medium.${character.thumbnailExt}"
                            Picasso.get().load(url).placeholder(R.drawable.comic_card).into(binding.characterDetailsImg)
                            binding.characterDetailsName.text = character.name
                            binding.characterDetailsDescription.text = character.description
                            Log.d("description",character.description)
                            binding.characterDetailsComics.text = character.comics.toString()
                        }
                    }
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}