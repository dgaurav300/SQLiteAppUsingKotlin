package com.example.managecustomerdetailsusingkotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var addButton:Button
    lateinit var printName:Button
    lateinit var enterName:TextView
    lateinit var enterAge:TextView
    lateinit var Name:TextView
    lateinit var Age:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton=findViewById(R.id.addName)
        enterName=findViewById(R.id.enterName)
        enterAge=findViewById(R.id.enterAge)
        printName=findViewById(R.id.printName)
        Name=findViewById(R.id.Name)
        Age=findViewById(R.id.Age)


        addButton.setOnClickListener{
            // below we have created
            // a new DBHelper class,
            // and passed context to it
            val db = DbHandler(this)

            // creating variables for values
            // in name and age edit texts
            val name = enterName.text.toString()
            val age = enterAge.text.toString()

            // calling method to add
            // name to our database
            db.addName(name, age)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            enterName.text=""
            enterAge.text=""
        }

        printName.setOnClickListener{
            // creating a DBHelper class
            // and passing context to it
            val db = DbHandler(this)
            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()
            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DbHandler.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DbHandler.AGE_COL)) + "\n")

            // moving our cursor to next
            // position and appending values
            while(cursor.moveToNext()){
                Name.append(cursor.getString(cursor.getColumnIndexOrThrow(DbHandler.NAME_COl)) + "\n")
                Age.append(cursor.getString(cursor.getColumnIndexOrThrow(DbHandler.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }


    }
}