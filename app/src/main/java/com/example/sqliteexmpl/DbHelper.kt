package com.example.sqliteexmpl

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME,
factory, DATABASE_VERSION) {

    companion object{
// here we have defined variables for our database
        val TABLE_NAME = "examp_table"
        val ID_COL = "id"
        val NAME_COl ="Name"
        val AGE_COL = "Age"


        // below is variable for database name
        private val DATABASE_NAME = "GEEKS_FOR_GEEKS"

        // below is the variable for database version
        private val DATABASE_VERSION = 1 }

    override fun onCreate(db: SQLiteDatabase?) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT" + ")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = ("DROP TABLE IF EXISTS " + TABLE_NAME)
        db?.execSQL(query)
    }
    fun addDetails(name: String, age:String){
        val values = ContentValues()
        values.put(NAME_COl, name)
        values.put(AGE_COL, age)
        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun getDetails(): Cursor? {
        val db = this.readableDatabase
        val result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
        return result
        db.execSQL("delete from "+ TABLE_NAME);

    }


}

