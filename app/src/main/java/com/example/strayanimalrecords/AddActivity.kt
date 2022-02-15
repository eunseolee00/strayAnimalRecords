package com.example.strayanimalrecords

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.health.HealthStats
import android.view.View
import android.widget.*

class AddActivity : AppCompatActivity() {

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
    lateinit var healthStats: RatingBar

    lateinit var save : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

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
        healthStats = findViewById(R.id.ratingBar)


        save = findViewById(R.id.save)



    }//onCreate

    fun saveData(view: View){

        AllAnimalNames.add(animalName.text.toString())
        AllLocations.add(location.text.toString())
        AllVolunteerNames.add(volunteerName.text.toString())
        AllHealthStatuses.add(healthStats.rating.toString())


        sharedPreferences = applicationContext.getSharedPreferences("com.example.strayAnimalRecords", Context.MODE_PRIVATE)

        if(rgFemale.isChecked){
            AllSexes.add("female")
        }else if (rgMale.isChecked){
            AllSexes.add("male")
        }

        if(rgCat.isChecked){
            AllAnimalTypes.add("cat")
        }else if(rgDog.isChecked){
            AllAnimalTypes.add("dog")
        }

        if(rgSorN.isChecked){
            AllNeuteredResults.add("SorN")
        }else if(rgNotSorN.isChecked){
            AllNeuteredResults.add("notSorN")
        }



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


}//AddActivity