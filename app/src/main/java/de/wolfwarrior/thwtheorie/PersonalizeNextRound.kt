package de.wolfwarrior.thwtheorie

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class PersonalizeNextRound : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var rndMode :Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var examMode :Switch

    private var rndModeBool: Boolean = false
    private var examModeBool: Boolean = false
    private var themeList: List<String> = listOf("1","2","3","4","5","6","7","8","9","10")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize_next_round)

        rndMode = findViewById(R.id.activity_personalize_next_round_rndmode)
        //rndMode.setOnCheckedChangeListener( CompoundButton  )


        rndMode.setOnCheckedChangeListener { _, _ ->
            rndModeBool = rndMode.isChecked

            if (rndModeBool) {
                examMode.isChecked = false
            }
        }

        examMode = findViewById(R.id.activity_personalize_next_round_exammode)

        examMode.setOnCheckedChangeListener { _, _ ->
            examModeBool = examMode.isChecked

            if (examModeBool) {
                rndMode.isChecked = false
            }
        }

        val listView = findViewById<ListView>(R.id.personalize_listview)
        listView.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, themeList)
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE


    }
}