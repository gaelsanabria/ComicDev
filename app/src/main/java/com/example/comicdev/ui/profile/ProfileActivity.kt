package com.example.comicdev.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.comicdev.R
import com.example.comicdev.databinding.ActivityProfileBinding
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    private val modalBottomSheet = CameraGalleryDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val items = listOf("Male", "Female", "None")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        binding.inputGender.setAdapter(adapter)

        val addPic: View = findViewById(R.id.add_picture)

        addPic.setOnClickListener {
            modalBottomSheet.show(supportFragmentManager, "Select Image resource DIALOG")
        }

        binding.btnSaveinfo.setOnClickListener{
            if (binding.textFullname.text?.isBlank() == true){
                binding.inputFullname.error = "Name cannot be empty"
            }
            if (binding.textAge.text?.isBlank() == true){
                binding.inputAge.error = "Age cannot be empty"
            }
        }

        binding.inputFullname.editText?.doOnTextChanged { _, _, _, _ ->
            binding.inputFullname.error = null
        }

        binding.inputAge.editText?.doOnTextChanged { _, _, _, _ ->
            binding.inputAge.error = null
        }
    }

    /*
    private fun CheckAllFields(): Boolean {
        if (etFirstName!!.length() == 0) {
            etFirstName!!.error = "This field is required"
            return false
        }
        if (etLastName!!.length() == 0) {
            etLastName!!.error = "This field is required"
            return false
        }
        if (etEmail!!.length() == 0) {
            etEmail!!.error = "Email is required"
            return false
        }
        if (etPassword!!.length() == 0) {
            etPassword!!.error = "Password is required"
            return false
        } else if (etPassword!!.length() < 8) {
            etPassword!!.error = "Password must be minimum 8 characters"
            return false
        }

        // after all validation return true.
        return true
    }*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        modalBottomSheet.onActivityResult(requestCode, resultCode, data)
        var bmp = data?.extras?.get("data") as Bitmap
        if (requestCode == 0){
            binding.profilePicture.setImageBitmap(bmp)
        } else if (requestCode == 1) {
            binding.profilePicture.setImageURI(data?.data)
        }
    }
}