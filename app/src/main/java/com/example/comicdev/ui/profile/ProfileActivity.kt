package com.example.comicdev.ui.profile

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityProfileBinding
import com.example.comicdev.entities.User
import com.example.comicdev.ui.main.MainActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val modalBottomSheet = CameraGalleryDialogFragment()

    private lateinit var viewModel: ProfileViewModel

    private var newPicGenerated = false

    var picture: Bitmap? = null

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.getUser.observe(this) { user ->
            if (user != null) {
                displayUserInfo(user)
            }
        }
        val items = listOf("Male", "Female", "None")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        binding.inputGender.setAdapter(adapter)

        binding.addPicture.setOnClickListener {
            modalBottomSheet.show(supportFragmentManager, "Select Image resource DIALOG")
        }

        /*
        when (intent.getStringExtra("calledFrom")){
            "firstTime" -> {}
            "editProfile" -> {
                setSupportActionBar(binding.profileToolbar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = "Edit Profile"
                binding.btnContinue.text = "Save Changes"
                binding.addPicture.setImageResource(R.drawable.ic_edit_pencil)
                binding.textFullname.setText(currentName)
                binding.inputGender.setText(currentGender, false)
                binding.textAge.setText(currentAge)
                    Glide.with(applicationContext)
                        .load(currentPicture)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .centerCrop()
                        .into(binding.profilePicture)
            }
            else -> {}
        }*/

        binding.btnContinue.setOnClickListener{
            val name = binding.textFullname.text.toString()
            val age = binding.textAge.text.toString()
            val gender = binding.inputGender.text.toString()
            if (name.isNotEmpty() &&
                age.isNotEmpty() &&
               gender.isNotEmpty()) {
                if (newPicGenerated) {
                    user = User(0, name, gender, age.toInt(),
                        picture?.let { it1 -> saveImgToInternalStorage(it1) })
                } else {
                    user = User(0, name, gender, age.toInt(), null)
                }
                viewModel.addUser(user)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else {
                if (name.isEmpty()){
                    binding.inputFullname.error = "Name cannot be empty"
                }
                if (age.isEmpty()){
                    binding.inputAge.error = "Age cannot be empty"
                }
                if (gender.isEmpty()){
                    binding.genderContainer.error = "You must choose a gender"
                } else {
                    binding.genderContainer.error = null
                }
            }
        }

        binding.inputFullname.editText?.doOnTextChanged { _, _, _, _ ->
            binding.inputFullname.error = null
        }

        binding.inputAge.editText?.doOnTextChanged { _, _, _, _ ->
            binding.inputAge.error = null
        }
    }

    private fun displayUserInfo(user: User) {
        binding.textFullname.setText(user.Name)
        binding.textAge.setText(user.Age.toString())
        binding.inputGender.setText(user.Gender)
        Glide.with(applicationContext)
            .load(user.Picture)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .centerCrop()
            .into(binding.profilePicture)

    }

    private fun saveImgToInternalStorage(bitmapImage: Bitmap): String? {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File = cw.getDir("picture", Context.MODE_PRIVATE)
        // Create imageDir
        val myPath = File(directory, "profile.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(myPath, false)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        return myPath.absolutePath
        Log.d("PATH", "${myPath.absolutePath}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modalBottomSheet.onActivityResult(requestCode, resultCode, data)
        if(data?.extras != null){
            var bmp = data?.extras?.get("data") as? Bitmap
            if (requestCode == 0){
                binding.profilePicture.setImageBitmap(bmp)
                picture = bmp
            } else if (requestCode == 1) {
                binding.profilePicture.setImageURI(data?.data)
                picture = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data);
            }
            newPicGenerated = true
            Log.d("TAG", "onActivityResult: $newPicGenerated")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}