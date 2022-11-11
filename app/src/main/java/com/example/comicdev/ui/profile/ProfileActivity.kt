package com.example.comicdev.ui.profile

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityProfileBinding
import com.example.comicdev.entities.User
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val modalBottomSheet = CameraGalleryDialogFragment()

    private lateinit var viewModel: ProfileViewModel

    var picture : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        val items = listOf("Male", "Female", "None")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        binding.inputGender.setAdapter(adapter)

        val addPic: View = findViewById(R.id.add_picture)

        addPic.setOnClickListener {
            modalBottomSheet.show(supportFragmentManager, "Select Image resource DIALOG")
        }

        binding.btnContinue.setOnClickListener{
            val name = binding.textFullname.text.toString()
            val age = binding.textAge.text.toString()
            val gender = binding.inputGender.text.toString()
            if (name.isNotEmpty() &&
                age.isNotEmpty() &&
               gender.isNotEmpty()){
                val user = User(0, name, gender, age.toInt(),
                    picture?.let { it1 -> saveImgToInternalStorage(it1) })
                viewModel.addUser(user)
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

    private fun saveImgToInternalStorage(bitmapImage: Bitmap): String? {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        val directory: File = cw.getDir("picture", Context.MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, "profile.jpg")
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
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
        return mypath.absolutePath
        Log.d("PATH", "${mypath.absolutePath}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modalBottomSheet.onActivityResult(requestCode, resultCode, data)
        var bmp = data?.extras?.get("data") as? Bitmap
        if (requestCode == 0){
            binding.profilePicture.setImageBitmap(bmp)
            picture = bmp
        } else if (requestCode == 1) {
            binding.profilePicture.setImageURI(data?.data)
            picture = MediaStore.Images.Media.getBitmap(this.contentResolver, data?.data);
        }
    }
}