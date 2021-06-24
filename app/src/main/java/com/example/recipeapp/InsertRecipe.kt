package com.example.recipeapp

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class InsertRecipe : AppCompatActivity() {
    private val pickImage = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_recipe)

        val back: Button = findViewById(R.id.btnBack)
        val save: Button = findViewById(R.id.btnSave)

        val name: EditText = findViewById(R.id.txtName)
        val ingredient: EditText = findViewById(R.id.txtIngredient)
        val steps: EditText = findViewById(R.id.txtSteps)
        val image: Button = findViewById(R.id.btnImage)
        val imageView : ImageView = findViewById(R.id.imgRecipe)

        back.setOnClickListener()
        {
            this.finish()
        }

        val context = this
        save.setOnClickListener()
        {
            if (name.text.toString().length > 0 && ingredient.text.toString().length > 0 && steps.text.toString().length > 0) {
//                val bitmap = (imageView.getDrawable() as BitmapDrawable).bitmap
                var saveRecipe =
                    Recipe(name.text.toString(), ingredient.text.toString(), steps.text.toString(), /*bitmap*/)
                var db = DatabaseHandler(context)
                db.insertData(saveRecipe)

                this.finish()
            } else {
                Toast.makeText(context, "Please fill in all Data", Toast.LENGTH_SHORT).show()
            }
        }

        image.setOnClickListener()
        {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView: ImageView = findViewById(R.id.imgRecipe)
        if(requestCode == pickImage)
        {
            imageView.setImageURI(data?.data)
        }
    }
}