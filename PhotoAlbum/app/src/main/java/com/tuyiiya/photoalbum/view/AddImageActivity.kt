package com.tuyiiya.photoalbum.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tuyiiya.photoalbum.R
import com.tuyiiya.photoalbum.databinding.ActivityAddImageBinding
import com.tuyiiya.photoalbum.util.ControlPermission

class AddImageActivity : AppCompatActivity() {
    lateinit var addImageBinding: ActivityAddImageBinding

    lateinit var activityResultLauncherForSelectImage: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        addImageBinding = ActivityAddImageBinding.inflate(layoutInflater)

        setContentView(addImageBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // register
        registerActivityForSelectImage()

        addImageBinding.imageViewAddImage.setOnClickListener {
            if (ControlPermission.checkPermission((this))) {
                //access the images
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                // startActivityForResult -> Before API 30
                activityResultLauncherForSelectImage.launch(intent)
            } else {
                if (Build.VERSION.SDK_INT >= 33) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                        1
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1
                    )
                }
            }
        }

        addImageBinding.buttonAdd.setOnClickListener {  }

        addImageBinding.toolbarAddImage.setNavigationOnClickListener {
            finish()
        }
    } // End of onCreate

    fun registerActivityForSelectImage() {

        activityResultLauncherForSelectImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            // result of the intent
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Access the images
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //Start Activity for Result
            activityResultLauncherForSelectImage.launch(intent)
        }
    }
}