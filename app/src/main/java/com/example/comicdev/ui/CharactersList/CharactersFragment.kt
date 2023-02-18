package com.example.comicdev.ui.CharactersList

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comicdev.R
import com.example.comicdev.databinding.FragmentCharactersBinding
import com.example.comicdev.domain.model.CharacterModel
import com.example.comicdev.ui.Character.CharacterViewModel
import com.example.comicdev.utils.CharacterListAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@WithFragmentBindings
@AndroidEntryPoint
class CharactersFragment : Fragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: FragmentCharactersBinding? = null

    private lateinit var searchTerm : String
    var valueRepeat = 3
    var paginatedValue = 0
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var layoutManager : GridLayoutManager
    private val viewModel : CharactersViewModel by viewModels()
    var list = arrayListOf<CharacterModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.characterRecyclerview
        layoutManager = GridLayoutManager(context,2)
        recyclerViewCharacters()
        viewModel.getAllCharactersData(paginatedValue)
        callAPI()
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1)
                {
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callAPI()
                }
            }
        })
        return root
    }

    private fun callAPI(){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(valueRepeat){
                viewModel._marvelValue.collect{value->
                    when {
                        value.error.isNotBlank() -> {
                            valueRepeat = 0
                            Toast.makeText(context, value.error, Toast.LENGTH_LONG).show()
                        }
                        value.charactersList.isNotEmpty() -> {
                            valueRepeat = 0
                            adapter.setData(value.charactersList as ArrayList<CharacterModel>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recyclerViewCharacters() {
        recyclerView = binding.characterRecyclerview
        adapter = context?.let { CharacterListAdapter(it, ArrayList()) }!!
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){
            searchTerm = query
        }
        if (searchTerm.isNotEmpty()) {
            search()
        }
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query!=null){
            searchTerm = query
        }
        if (searchTerm.isNotEmpty()) {
            search()
        }
        return true
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun search(){
        viewModel.getSearchedCharacters(searchTerm)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel._marvelValue.collect{
                when{
                    it.isLoading -> {
                    }
                    it.error.isNotBlank() -> {
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                    it.charactersList.isNotEmpty() -> {
                        adapter.setData(it.charactersList as ArrayList<CharacterModel>)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}