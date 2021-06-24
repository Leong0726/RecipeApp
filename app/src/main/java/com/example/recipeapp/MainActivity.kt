package com.example.recipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById<Button>(R.id.btn_next_page)
        val login : Button = findViewById<Button>(R.id.btnLogin)
        val id : EditText = findViewById<EditText>(R.id.txtID)
        val password : EditText = findViewById<EditText>(R.id.txtPassword)

        button.setOnClickListener {
            val intent = Intent(this, SpinnerActivity::class.java)
            SpinnerActivity.Global.is_admin = false
            startActivity(intent)
        }

        login.setOnClickListener{
            if(id.text.toString() == "admin" && password.text.toString() == "admin")
            {
                val intent = Intent(this, SpinnerActivity::class.java)
                SpinnerActivity.Global.is_admin = true
                startActivity(intent)
            }
        }
    }
}