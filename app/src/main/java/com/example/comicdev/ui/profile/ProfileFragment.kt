package com.example.comicdev.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.comicdev.databinding.FragmentProfileBinding
import com.example.comicdev.db.AppDatabase
import com.example.comicdev.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private lateinit var viewModel: ProfileViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var user: LiveData<User>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.getUser.observe(viewLifecycleOwner){ user ->
            displayData(user)
        }

        binding.btnEditProfile.setOnClickListener{
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayData(user: User) {

            binding.textUsername.text = user.Name
            binding.textAge.text = user.Age.toString()
            //binding.cardProfile.background =
            user.Picture?.let { Log.d("User pic", it) }
            context?.let {
                Glide.with(it)
                    .load(user.Picture)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(binding.cardImage)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateUser()
    }

    private fun updateUser() {
        //appDb = AppDatabase.getDatabase(requireContext())
        //loadUser()
    }
}