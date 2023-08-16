package com.example.sqliteexmpl

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

       val addDetailsBtn = findViewById<Button>(R.id.addDetails)
        val printDetailsBtn = findViewById<Button>(R.id.printDetails)
        val nameEtv = findViewById<EditText>(R.id.name)
        val ageEtv = findViewById<EditText>(R.id.age)
        addDetailsBtn.setOnClickListener(){
            val db = DbHelper(this, factory = null)
            val name = nameEtv.text.toString()
            val age = ageEtv.text.toString()
            db.addDetails(name, age)
            Toast.makeText(this, "Name and Age Details are added to Database", Toast.LENGTH_LONG).show()
            nameEtv.text.clear()
            ageEtv.text.clear()

            }
        printDetailsBtn.setOnClickListener(){
            val db = DbHelper(this, factory = null)
            val cursor = db.getDetails()
           /* cursor!!.moveToFirst()
            val first = cursor.getString(cursor.getColumnIndex(DbHelper.NAME_COl))
            val second = cursor.getString(cursor.getColumnIndex(DbHelper.AGE_COL))
            Toast.makeText(this, "$first and $second are retrieved from Database", Toast.LENGTH_LONG).show()*/
            var isRecordRetrieved = false

            while (cursor!!.moveToNext()) {
                val first = cursor.getString(cursor.getColumnIndex(DbHelper.NAME_COl))
                val second = cursor.getString(cursor.getColumnIndex(DbHelper.AGE_COL))
                isRecordRetrieved = true
                Toast.makeText(this, "$first and $second are retrieved from Database", Toast.LENGTH_LONG).show()
                break
            }


            if (!isRecordRetrieved) {

                Toast.makeText(this, "No Records Retrieved from Database", Toast.LENGTH_LONG).show()
            }

        }
    }
}


