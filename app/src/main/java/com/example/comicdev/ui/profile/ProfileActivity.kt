package com.example.comicdev.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.comicdev.R

class ProfileActivity : AppCompatActivity() {

    private val mImageView:ImageView
        get() = findViewById(R.id.profile_picture)
    var genders = arrayOf("Gender", "Male", "Female", "Unspecified")
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        spinner = findViewById(R.id.input_gender)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, genders)
            spinner.adapter = adapter }
        val add_pic: View = findViewById(R.id.add_picture)

        add_pic.setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose an option")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("Gallery") { dialog, which ->
                val pickPhoto = Intent(
                    Intent.ACTION_PICK)
                    pickPhoto.type = "image/*"
                startActivityForResult(pickPhoto, 1) //one can be replaced with any action code

            }

            builder.setNeutralButton("Camera") { dialog, which ->
                val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, 0)
            }
            builder.show()
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