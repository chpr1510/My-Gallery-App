package com.example.mygallery

class Image {
    var imagePath:String?=null
    var imageNmae:String?=null

    constructor(imagePath: String?, imageNmae: String?) {
        this.imagePath = imagePath
        this.imageNmae = imageNmae
    }
    constructor()
    {}
}