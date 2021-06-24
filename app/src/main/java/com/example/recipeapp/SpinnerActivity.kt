package com.example.recipeapp

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SpinnerActivity : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var ingrediant: TextView
    lateinit var steps: TextView
    lateinit var image: ImageView

    public class Global : Application(){
        companion object{
            var is_admin : Boolean = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        val fab : FloatingActionButton = findViewById<FloatingActionButton>(R.id.fabEdit)

        if(!Global.is_admin)
        {
            fab.visibility = View.GONE
        }
        option = findViewById(R.id.sp_option) as Spinner
        ingrediant = findViewById(R.id.lblIngredient) as TextView
        steps = findViewById(R.id.lblSteps) as TextView
        image = findViewById(R.id.recipe_image) as ImageView

//        val w: Int = 0
//        val h: Int = 0
//        val conf = Bitmap.Config.ARGB_8888
//        val bmp = Bitmap.createBitmap(w, h, conf)

        var context = this
        val lsIngredient : MutableList<String> = ArrayList()
        val lsSteps : MutableList<String> = ArrayList()
        //val lsImage : MutableList<Bitmap> = ArrayList()
        val options : MutableList<String> = ArrayList()
        options.add("Please select an recipe...")
        lsIngredient.add("")
        lsSteps.add("")
        //lsImage.add(bmp)
        var db = DatabaseHandler(context)
        var data = db.readData()
        for (i in 0..(data.size - 1))
        {
            options.add(data.get(i).tmp_name.toString())
            lsSteps.add(data.get(i).tmp_steps.toString())
            lsIngredient.add(data.get(i).tmp_ingredient.toString())
            //lsImage.add(data.get(i).tmp_image)
        }

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        fab.setOnClickListener{
            val intent = Intent(this, InsertRecipe::class.java)
            startActivity(intent)
        }

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ingrediant.text = lsIngredient.elementAt(position)
                steps.text = lsSteps.elementAt(position)
                //image.setImageBitmap(lsImage.elementAt(position))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                option.setSelection(0)
                ingrediant.text = "Please select an recipe..."
            }
        }
    }
}
