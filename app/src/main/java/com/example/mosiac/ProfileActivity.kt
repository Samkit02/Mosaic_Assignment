package com.example.mosiac

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var profileImageView: ImageView
    private var imageUri: Uri? = null
    private lateinit var nameEditText: EditText
    private lateinit var educationEditText: EditText
    private lateinit var workExperienceEditText: EditText

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_IMAGE_PICK = 2
        private const val IMAGE_URI_KEY = "imageUri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileImageView = findViewById(R.id.profileImageView)
        nameEditText = findViewById(R.id.nameEditText)
        educationEditText = findViewById(R.id.educationEditText)
        workExperienceEditText = findViewById(R.id.workExperienceEditText)
        val saveButton: Button = findViewById(R.id.saveButton)
        val pickImageButton: Button = findViewById(R.id.pickImageButton)
        val takePhotoButton: Button = findViewById(R.id.takePhotoButton)

        sharedPreferences = getSharedPreferences("ProfileData", Context.MODE_PRIVATE)

        loadProfileData()

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val education = educationEditText.text.toString()
            val workExperience = workExperienceEditText.text.toString()

            saveProfileData(name, education, workExperience)

            Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
        }

        pickImageButton.setOnClickListener {
            val pickImageIntent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickImageIntent, REQUEST_IMAGE_PICK)
        }

        takePhotoButton.setOnClickListener {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePhotoIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(takePhotoIntent, REQUEST_IMAGE_CAPTURE)
            } else {
                Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    profileImageView.setImageBitmap(imageBitmap)
                    imageUri = saveImageUriToLocal(data?.extras?.get("data") as Bitmap)
                }
                REQUEST_IMAGE_PICK -> {
                    val selectedImageUri = data?.data
                    profileImageView.setImageURI(selectedImageUri)
                    imageUri = selectedImageUri
                }
            }
        }
    }

    private fun saveProfileData(name: String, education: String, workExperience: String) {
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.putString("education", education)
        editor.putString("workExperience", workExperience)
        // Save the image URI
        editor.putString(IMAGE_URI_KEY, imageUri.toString())
        editor.apply()
    }

    private fun loadProfileData() {
        val name = sharedPreferences.getString("name", "")
        val education = sharedPreferences.getString("education", "")
        val workExperience = sharedPreferences.getString("workExperience", "")
        val imageUriString = sharedPreferences.getString(IMAGE_URI_KEY, null)

        nameEditText.setText(name)
        educationEditText.setText(education)
        workExperienceEditText.setText(workExperience)

        imageUriString?.let {
            imageUri = Uri.parse(it)
            profileImageView.setImageURI(imageUri)
        }
    }

    private fun saveImageUriToLocal(bitmap: Bitmap): Uri {
        val imageFile = File(cacheDir, "profile_image.jpg")
        val outputStream = FileOutputStream(imageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return Uri.fromFile(imageFile)
    }
}
