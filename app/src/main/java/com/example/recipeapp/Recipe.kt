package com.example.recipeapp

import android.graphics.Bitmap
import java.sql.Blob

class Recipe {

//    val w: Int = 0
//    val h: Int = 0
//    val conf = Bitmap.Config.ARGB_8888
//    val bmp = Bitmap.createBitmap(w, h, conf)

    var id : Int = 0
    var tmp_name : String = ""
    var tmp_ingredient : String = ""
    var tmp_steps : String = ""
//    var tmp_image : Bitmap = bmp

    constructor(name : String, ingredient : String, steps : String/*, image : Bitmap*/)
    {
        this.tmp_name = name
        this.tmp_ingredient = ingredient
        this.tmp_steps = steps
//        this.tmp_image = image
    }

    constructor()
    {

    }
}