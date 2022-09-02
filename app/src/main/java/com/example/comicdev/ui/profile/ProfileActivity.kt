package com.example.comicdev.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.comicdev.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class ProfileActivity : AppCompatActivity() {

    private val mImageView:ImageView
        get() = findViewById(R.id.profile_picture)
    private lateinit var inputGender:AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        inputGender = findViewById(R.id.input_gender)

        val items = listOf("Male", "Female", "None")
        val adapter = ArrayAdapter(applicationContext, R.layout.list_item, items)
        inputGender.setAdapter(adapter)

        val add_pic: View = findViewById(R.id.add_picture)

        add_pic.setOnClickListener {
            val modalBottomSheet = CameraGalleryDialogFragment()
            modalBottomSheet.show(supportFragmentManager, "Select Image resource DIALOG")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0){
            var bmp = data?.extras?.get("data") as Bitmap
            mImageView.setImageBitmap(bmp)
        } else if (requestCode == 1) {
            mImageView.setImageURI(data?.data)
        }
    }
}