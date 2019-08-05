package com.example.intentdemo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var placeHolderImgView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        placeHolderImgView = findViewById(R.id.placeholderImgView) as ImageView

        placeHolderImgView.setOnClickListener {

        }

    }


}
