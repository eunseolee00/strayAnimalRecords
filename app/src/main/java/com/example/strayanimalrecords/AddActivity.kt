package com.example.strayanimalrecords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    lateinit var animalName : EditText
    lateinit var last : EditText
    lateinit var phone : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }//onCreate
}//AddActivity