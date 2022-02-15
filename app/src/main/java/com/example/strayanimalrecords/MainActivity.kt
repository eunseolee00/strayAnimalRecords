//Author: Eunseo Elsa Lee, Karen Ren
package com.example.strayanimalrecords

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import java.util.ArrayList


lateinit var arrayAdapter: ArrayAdapter<*>
var AllAnimalNames = ArrayList<String>()
var AllLocations = ArrayList<String>()
var AllVolunteerNames = ArrayList<String>()
var AllSexes = ArrayList<String>()
var AllNeuteredResults = ArrayList<String>()
var AllAnimalTypes = ArrayList<String>()
var AllHealthStatuses = ArrayList<String>()
lateinit var sharedPreferences: SharedPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }//onCreate

    fun newAnimal(view: View?) {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }//newAnimal

    fun viewExisting(view: View?) {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }//viewExisting


}//MainActivity