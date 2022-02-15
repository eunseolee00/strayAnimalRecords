package com.example.strayanimalrecords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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


    }
}