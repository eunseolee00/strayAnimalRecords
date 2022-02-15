package com.example.strayanimalrecords

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.ArrayList



class ListActivity : AppCompatActivity() {
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listView = findViewById(R.id.listView)
        getData()

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, AllAnimalNames)
        listView.adapter = arrayAdapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val intent = Intent(applicationContext, EditorActivity::class.java)
                intent.putExtra("Loc", i)
                startActivity(intent)
            }//OnItemClickListener

        listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, i, l ->

                val itemDelete = i
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure!")
                    .setMessage("Do you want to delete this animal's file?")
                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                        AllAnimalNames.removeAt(itemDelete)
                        AllLocations.removeAt(itemDelete)
                        AllVolunteerNames.removeAt(itemDelete)
                        AllNeuteredResults.removeAt(itemDelete)
                        AllSexes.removeAt(itemDelete)
                        AllAnimalTypes.removeAt(itemDelete)
                        AllHealthStatuses.removeAt(itemDelete)
                        arrayAdapter.notifyDataSetChanged()
                        sharedPreferences.edit()
                            .putString("animalNames", ObjectSerializer.serialize(AllAnimalNames))
                            .apply()
                        sharedPreferences.edit()
                            .putString("locations", ObjectSerializer.serialize(AllLocations))
                            .apply()
                        sharedPreferences.edit().putString(
                            "volunteerNames", ObjectSerializer.serialize(AllVolunteerNames))
                            .apply()
                        sharedPreferences.edit().putString(
                            "neuterOrSpay", ObjectSerializer.serialize(AllNeuteredResults))
                            .apply()
                        sharedPreferences.edit().putString(
                            "sexes", ObjectSerializer.serialize(AllSexes))
                            .apply()
                        sharedPreferences.edit().putString(
                            "healthStatuses", ObjectSerializer.serialize(AllHealthStatuses))
                            .apply()
                        sharedPreferences.edit().putString(
                            "animalTypes", ObjectSerializer.serialize(AllAnimalTypes))
                            .apply()
                    }//setPositiveButton
                    .setNegativeButton("No", null)
                    .show()

                return@OnItemLongClickListener true
            }//OnItemCLongClickListener
    }//onCreate

    fun getData() {
        sharedPreferences = applicationContext.getSharedPreferences(
            "com.example.strayAnimalRecords", Context.MODE_PRIVATE
        )

        var newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("animalNames", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllAnimalNames = ArrayList(newAnimals)
        }//if

        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("location", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllLocations = ArrayList(newAnimals)
        }//if

        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("volunteers", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllVolunteerNames = ArrayList(newAnimals)
        }//if

        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("neuteredResult", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllNeuteredResults = ArrayList(newAnimals)
        }//if

        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("Gender", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllSexes = ArrayList(newAnimals)
        }//if



        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("Health", ObjectSerializer.serialize(ArrayList<String>()))
            ) as ArrayList<String?>



        if (newAnimals.size != 0) {
            AllHealthStatuses = ArrayList(newAnimals)
        }//if




        newAnimals = ArrayList<String?>()
        newAnimals = ObjectSerializer
            .deserialize(
                sharedPreferences
                    .getString("Type", ObjectSerializer.serialize(ArrayList<Int>()))
            ) as ArrayList<String?>

        if (newAnimals.size != 0) {
            AllAnimalTypes = ArrayList(newAnimals)
        }//if
    }//getData

}//listActivity