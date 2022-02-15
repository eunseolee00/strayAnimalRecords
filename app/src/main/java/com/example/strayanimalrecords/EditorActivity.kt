package com.example.strayanimalrecords

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
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
    var flag = true

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
        health.numStars = 3


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

        AllAnimalNames.add(animalName.text.toString())
        AllLocations.add(location.text.toString())
        AllVolunteerNames.add(volunteerName.text.toString())
        AllHealthStatuses.add(health.rating.toString())
        arrayAdapter.notifyDataSetChanged()


        sharedPreferences = applicationContext.getSharedPreferences("com.example.strayAnimalRecords", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("animalNames", ObjectSerializer.serialize(AllAnimalNames)).apply()
        sharedPreferences.edit().putString("Gender",ObjectSerializer.serialize(AllSexes)).apply()
        sharedPreferences.edit().putString("location", ObjectSerializer.serialize(AllLocations)).apply()
        sharedPreferences.edit().putString("Type",ObjectSerializer.serialize(AllAnimalTypes)).apply()
        sharedPreferences.edit().putString("neuteredResult",ObjectSerializer.serialize(AllNeuteredResults)).apply()
        sharedPreferences.edit().putString("Health",ObjectSerializer.serialize(AllHealthStatuses)).apply()
        sharedPreferences.edit().putString("volunteers", ObjectSerializer.serialize(
            AllVolunteerNames)).apply()

        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }//saveData


    fun hide (view : View) {

        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (flag) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }
        else {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
        }
        flag = !flag
    }//hide
}