package com.example.strayanimalrecords

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class EditorActivity : AppCompatActivity() {
    lateinit var animalName : EditText
    lateinit var location : EditText

    lateinit var rgAnimal : RadioGroup
    lateinit var rgCat : RadioButton
    lateinit var rgDog : RadioButton

    lateinit var rgGender : RadioGroup
    lateinit var rgFemale : RadioButton
    lateinit var rgMale : RadioButton

    lateinit var rgNeuteredResults : RadioGroup
    lateinit var rgSorN : RadioButton
    lateinit var rgNotSorN : RadioButton

    lateinit var volunteerName : EditText
    lateinit var health : RatingBar

    lateinit var save : Button

    var loc = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)


        animalName = findViewById(R.id.animalName)
        location  = findViewById(R.id.location)
        rgAnimal = findViewById(R.id.Type)
        rgCat = findViewById(R.id.cat)
        rgDog = findViewById(R.id.dog)

        rgGender = findViewById(R.id.gender)
        rgFemale = findViewById(R.id.female)
        rgMale = findViewById(R.id.male)

        rgNeuteredResults = findViewById(R.id.neuteredResult)
        rgSorN = findViewById(R.id.SorN)
        rgNotSorN = findViewById(R.id.notSorN)
        volunteerName = findViewById(R.id.volunteerName)
        health = findViewById(R.id.ratingBar)

        save = findViewById(R.id.save)
        loc = intent.getIntExtra("Loc", -1)


        animalName.setText(AllAnimalNames[loc])
        volunteerName.setText(AllVolunteerNames[loc])
        location.setText(AllLocations[loc])
        health.rating = AllHealthStatuses[loc].toFloat()


        if(AllSexes[loc] == "female"){
            rgFemale.isChecked = true
        }else if(AllSexes[loc] == "male"){
            rgMale.isChecked = true
        }

        if(AllAnimalTypes[loc] == "cat"){
            rgCat.isChecked = true
        }else if(AllAnimalTypes[loc] == "dog"){
            rgDog.isChecked = true
        }

        if(AllNeuteredResults[loc] == "SorN"){
            rgSorN.isChecked = true
        }else if(AllNeuteredResults[loc] == "notSorN"){
            rgNotSorN.isChecked = true
        }


    }//onCreate

    fun saveData(view: View){

        AllAnimalTypes[loc] = animalName.text.toString()
        AllLocations[loc] = location.text.toString()
        AllVolunteerNames[loc] = volunteerName.text.toString()
        AllHealthStatuses[loc] = health.rating.toString()

        sharedPreferences = applicationContext.getSharedPreferences("com.example.strayAnimalRecords", Context.MODE_PRIVATE)

        if(rgFemale.isChecked){
            AllSexes[loc] = "female"
        }else if (rgMale.isChecked){
            AllSexes[loc] = "male"
        }

        if(rgCat.isChecked){
            AllAnimalTypes[loc] = "cat"
        }else if(rgDog.isChecked){
            AllAnimalTypes[loc] = "dog"
        }

        if(rgSorN.isChecked){
            AllNeuteredResults[loc] = "SorN"
        }else if(rgNotSorN.isChecked){
            AllNeuteredResults[loc] = "notSorN"
        }

        arrayAdapter.notifyDataSetChanged();

        sharedPreferences.edit().putString("animalNames", ObjectSerializer.serialize(AllAnimalNames)).apply()
        sharedPreferences.edit().putString("Gender",ObjectSerializer.serialize(AllSexes)).apply()
        sharedPreferences.edit().putString("location", ObjectSerializer.serialize(AllLocations)).apply()
        sharedPreferences.edit().putString("Type",ObjectSerializer.serialize(AllAnimalTypes)).apply()
        sharedPreferences.edit().putString("neuteredResult",ObjectSerializer.serialize(AllNeuteredResults)).apply()
        sharedPreferences.edit().putString("Health",ObjectSerializer.serialize(AllHealthStatuses)).apply()


        sharedPreferences.edit().putString("volunteers", ObjectSerializer.serialize(
            AllVolunteerNames)).apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }//saveData
}