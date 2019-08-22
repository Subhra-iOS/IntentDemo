package com.example.intentdemo

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import android.os.Build
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private val  PHOTO_REQUEST_CODE = 1

    private lateinit var placeHolderImgView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        placeHolderImgView = findViewById(R.id.placeholderImgView)

        placeHolderImgView.setOnClickListener {
            takePictureWithCamera()
        }

    }

    /***
     * invoke camera to take picture
     * @author : Subhra Roy
     */
    private fun takePictureWithCamera(){

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val imgDirectoryPath = File(filesDir, "PhotoFolder")
        var newPhotoPath = File(imgDirectoryPath, "item_image.jpg")

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
