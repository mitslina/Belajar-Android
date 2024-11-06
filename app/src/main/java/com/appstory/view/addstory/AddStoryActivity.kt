package com.appstory.view.addstory

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.appstory.R
import com.appstory.databinding.ActivityAddStoryBinding
import com.appstory.view.ViewModelFactory
import com.appstory.view.getImageUri
import com.appstory.view.liststory.ListStoryActivity
import com.appstory.view.reduceFileImage
import com.appstory.view.uriToFile
import com.google.android.gms.maps.model.LatLng
import java.io.File

class AddStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStoryBinding
    private var currentImageUri: Uri? = null
    private var currentLocation: LatLng? = null

    private val viewModel by viewModels<AddStoryViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this,
                    getString(R.string.permission_request_granted), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,
                    getString(R.string.permission_request_denied), Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
        binding.locationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                getMyLocation()
            } else {
                currentLocation = null
            }
        }
        binding.uploadButton.setOnClickListener { uploadStory() }
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                currentLocation = LatLng(location.latitude, location.longitude)
            } else {
                Toast.makeText(this, getString(R.string.unable_to_get_location), Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.addCallback(this) {
                    finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Toast.makeText(this, getString(R.string.no_media_selected), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun uploadStory() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            val description = binding.etDescription.text.toString()

            observeViewModel(imageFile, description, currentLocation)
        } ?: showToast(getString(R.string.empty_image_warning))
    }

    private fun observeViewModel(imageFile: File, description: String, location: LatLng?) {
        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
        viewModel.addNewStory(imageFile, description, location?.latitude, location?.longitude)
        viewModel.isError.observe(this, Observer { isError ->
            isError?.let { error ->
                viewModel.message.observe(this, Observer { message ->
                    if (error) {
                        showAlertDialog(getString(R.string.waduw), message, getString(R.string.coba_lagi))
                    } else {
                        showAlertDialog(getString(R.string.yeah), message, getString(R.string.lanjut)) {
                            moveToList()
                        }
                    }
                })
            }
        })
    }

    private fun showAlertDialog(title: String, message: String?, buttonText: String, onPositive: (() -> Unit)? = null) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonText) { dialog, _ ->
                onPositive?.invoke()
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun moveToList() {
        val intent = Intent(this, ListStoryActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.isVisible = isLoading
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}