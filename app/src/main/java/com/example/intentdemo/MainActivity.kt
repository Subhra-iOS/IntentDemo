package com.example.intentdemo

import android.Manifest
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.jar.Manifest.*


class MainActivity : AppCompatActivity() {

    private val  PHOTO_REQUEST_CODE = 1

    private lateinit var placeHolderImgView : ImageView

    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        placeHolderImgView = findViewById(R.id.placeholderImgView)

        placeHolderImgView.setOnClickListener {
            takePictureWithCamera()
        }


        if (hasPermission()){

            requestPermission()
        }
    }

    /**
     * Check whether permissions are allowed or not
     * @author : Subhra Roy
     */

    private fun hasPermission() : Boolean{

        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    /**
     * Request for giving permission
     * @author : Subhra Roy
     */
    private fun requestPermission(){
        ActivityCompat.requestPermissions(this,permissions,0)
    }

    /***
     * invoke camera to take picture
     * @author : Subhra Roy
     */
    private fun takePictureWithCamera(){

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val imgDirectoryPath = File(filesDir, "images")
        val newPhotoPath = File(imgDirectoryPath, "default_image.jpg")

        if(newPhotoPath.exists()){
           val status = newPhotoPath.delete()

            when (status){
                true -> "Deleted"
                else -> "Error while delete"
            }
        }else{

            newPhotoPath.parentFile.mkdirs()
        }

        val selectedPhotoPath = FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID + ".fileprovider",
            newPhotoPath)

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedPhotoPath)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val clip = ClipData.newUri(contentResolver, "A photo", selectedPhotoPath)
            cameraIntent.clipData = clip
            cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }

        startActivityForResult(cameraIntent,PHOTO_REQUEST_CODE)

    }

    /***
     * did tap on button
     * @author : Subhra Roy
     */
    fun didTapPhotoDataOnClick(view : View){


    }


}

