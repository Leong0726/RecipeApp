package com.example.recipeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext

val DATABASE_NAME = "test_assessment"
val TABLE_NAME = "recipe"
val COL_NAME = "name"
val COL_INGREDIENT = "ingredient"
val COL_STEPS = "steps"
val COL_ID = "id"
//val COL_IMAGE = "images"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            COL_NAME + " VARCHAR(256), " + COL_INGREDIENT + " VARCHAR(256), " + COL_STEPS + " VARCHAR(256))" /*+ "," +
                            COL_IMAGE + " BLOB"*/;

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(insert: Recipe){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, insert.tmp_name)
        cv.put(COL_INGREDIENT,insert.tmp_ingredient)
        cv.put(COL_STEPS, insert.tmp_steps)
//        cv.put(COL_IMAGE, insert.tmp_image)
        var result = db.insert(TABLE_NAME, null,cv)
        if(result == -1.toLong())
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    fun readData() : MutableList<Recipe>{
        var list : MutableList<Recipe> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM  " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst())
        {
            do{
                var rcp = Recipe()
                rcp.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                rcp.tmp_name = result.getString(result.getColumnIndex(COL_NAME))
                rcp.tmp_ingredient = result.getString(result.getColumnIndex(COL_INGREDIENT))
                rcp.tmp_steps = result.getString(result.getColumnIndex(COL_STEPS))
                list.add(rcp)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }
}