package com.example.mygallery

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var imageRecycler:RecyclerView?=null
    private var Progressbar:ProgressBar?=null
    private  var allPictures:ArrayList<Image>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageRecycler = findViewById(R.id.image_recycler)
        Progressbar = findViewById(R.id.RecyclerProgress)

        imageRecycler?.layoutManager = GridLayoutManager(this,3)
        imageRecycler?.setHasFixedSize(true)

        if(ContextCompat.checkSelfPermission(this@MainActivity,Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                77)
        }

        allPictures = ArrayList()
        if(allPictures!!.isEmpty()){
            Progressbar?.visibility = View.VISIBLE
            allPictures = getAllImages()
            imageRecycler?.adapter = ImageAdapter(this,allPictures!!)
            Progressbar?.visibility = View.GONE
        }


    }

    private fun getAllImages(): ArrayList<Image>? {
        val images = ArrayList<Image>()
        val allImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.ImageColumns.DISPLAY_NAME)
        val cursor = this@MainActivity.contentResolver.query(allImageUri,projection,null,null,null)
        try {
                cursor!!.moveToFirst()
            do{
                val image=Image()
                image.imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.imageNmae = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while(cursor.moveToNext())
        cursor.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return images
    }

}