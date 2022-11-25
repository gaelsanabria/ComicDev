package com.example.comicdev.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
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

    private lateinit var appDb : AppDatabase
    private lateinit var viewModel: ProfileViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        appDb = AppDatabase.getDatabase(requireContext())
        loadUser()

        val root: View = binding.root

        //val textView: TextView = binding.textProfile
        profileViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }

        binding.btnEditProfile.setOnClickListener{
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            intent.putExtra("calledFrom", "editProfile")
                .putExtra("name", binding.textUsername.text)
                .putExtra("age", binding.textAge.text)
                .putExtra("gender", user.Gender)
                .putExtra("picture", user.Picture)
            startActivityForResult(intent, 1)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadUser() {
        GlobalScope.launch {
            user = appDb.userDao().loadUser()
            Log.d("User Data",user.toString())
            displayData(user)
        }
    }

    private suspend fun displayData(user: User) {
        withContext(Dispatchers.Main){

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateUser()
    }

    private fun updateUser() {
        appDb = AppDatabase.getDatabase(requireContext())
        loadUser()
    }
}