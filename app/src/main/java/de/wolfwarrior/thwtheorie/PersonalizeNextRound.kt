package de.wolfwarrior.thwtheorie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch

lateinit var rndMode :Switch
lateinit var examMode :Switch

var rndModeBool: Boolean = false
var examModeBool: Boolean = false
class PersonalizeNextRound : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize_next_round)

        rndMode = findViewById(R.id.activity_personalize_next_round_rndmode)
        examMode = findViewById(R.id.activity_personalize_next_round_exammode)
    }


    fun setRndMode(view:View){
        rndModeBool = rndMode.isChecked

        if (rndModeBool){
            examMode.isChecked = false
        }
    }

    fun setExamMode(view:View){
        examModeBool = examMode.isChecked

        if (examModeBool){
            rndMode.isChecked = false
        }

    }
}