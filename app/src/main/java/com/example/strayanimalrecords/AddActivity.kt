package com.example.strayanimalrecords

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

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

        save = findViewById(R.id.save)



    }//onCreate

    fun saveData(view: View){
        AllAnimalNames.add(animalName.text.toString())
        AllLocations.add(location.text.toString())
        AllVolunteerNames.add(volunteerName.text.toString())

        sharedPreferences = applicationContext.getSharedPreferences("com.example.strayAnimalRecords", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("animalNames", ObjectSerializer.serialize(AllAnimalNames)).apply()

        rgGender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, i ->
                if (i == R.id.female){
                    sharedPreferences.edit().putInt("Gender",0).apply()
                    AllSexes.add("cat")
                }else if(i == R.id.male){
                    sharedPreferences.edit().putInt("Gender",1).apply()
                    AllSexes.add("dog")
                }
            }//onCheckedChangeListener
        )//setOnCheckedChangeListener
        val gender = sharedPreferences.getInt("Gender", -1)
        if (gender == 0){
            rgFemale.isChecked = true
        }else if (gender == 1){
            rgMale.isChecked = true
        }

        sharedPreferences.edit().putString("location", ObjectSerializer.serialize(AllLocations)).apply()

        rgAnimal.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, i ->
                if (i == R.id.cat){
                    sharedPreferences.edit().putInt("Type",0).apply()
                    AllAnimalTypes.add("0")
                }else if(i == R.id.dog){
                    sharedPreferences.edit().putInt("Type",1).apply()
                    AllAnimalTypes.add("1")
                }
            }
        )
        val animalType = sharedPreferences.getInt("Type", -1)
        if (animalType == 0){
            rgCat.isChecked = true
        }else if (gender == 1){
            rgDog.isChecked = true
        }

        rgNeuteredResults.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { _, i ->
                if (i == R.id.SorN){
                    sharedPreferences.edit().putInt("SorN",0).apply()
                    AllNeuteredResults.add("0")
                }else if(i == R.id.notSorN){
                    sharedPreferences.edit().putInt("SorN",1).apply()
                    AllNeuteredResults.add("1")
                }
            }
        )
        val neuteredResults = sharedPreferences.getInt("SorN", -1)
        if (neuteredResults == 0){
            rgSorN.isChecked = true
        }else if (gender == 1){
            rgNotSorN.isChecked = true
        }

        sharedPreferences.edit().putString("volunteers", ObjectSerializer.serialize(
            AllVolunteerNames)).apply()

        arrayAdapter.notifyDataSetChanged();

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }//saveData


}//AddActivity