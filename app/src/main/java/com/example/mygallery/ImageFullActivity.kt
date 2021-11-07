package com.example.mygallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ImageFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)

        val imagepath = intent.getStringExtra("path")
        val imagename = intent.getStringExtra("name")
        supportActionBar?.setTitle(imagename)

        Glide.with(this)
            .load(imagepath)
            .into(findViewById(R.id.imageView))
    }
}