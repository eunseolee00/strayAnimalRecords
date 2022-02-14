package com.example.strayanimalrecords

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import java.util.ArrayList

lateinit var arrayAdapter: ArrayAdapter<*>
var animalName = ArrayList<String>()
var location = ArrayList<String>()
var volunteerName = ArrayList<String>()
var sex = ArrayList<String>()
var neutered = ArrayList<String>()
var animalType = ArrayList<String>()
var healthStatus = ArrayList<Int>()
lateinit var sharedPreferences: SharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }//onCreate
}//MainActivity