package com.example.intentdemo

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val  PHOTO_REQUEST_CODE = 1

    private lateinit var placeHolderImgView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        placeHolderImgView = findViewById(R.id.placeholderImgView) as ImageView

        placeHolderImgView.setOnClickListener {
            takePictureWithCamera()
        }

    }

    /***
     * invoke camera to take picture
     * @author : Subhra Roy
     */
    private fun takePictureWithCamera(){

        println("Test")
    }

    /***
     * did tap on button
     * @author : Subhra Roy
     */
    fun didTapPhotoDataOnClick(view : View){


    }


}
