package de.wolfwarrior.thwtheorie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class PersonalizeNextRound : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var rndMode: Switch

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var examMode: Switch


    private val listOfSelectedItems = arrayListOf<Int>()

    private var rndModeBool: Boolean = false
    private var examModeBool: Boolean = false
    private lateinit var themeList: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize_next_round)

        themeList = listOf(
            resources.getString(R.string.choose_theme_one),
            resources.getString(R.string.choose_theme_two),
            resources.getString(R.string.choose_theme_three),
            resources.getString(R.string.choose_theme_four),
            resources.getString(R.string.choose_theme_five),
            resources.getString(R.string.choose_theme_six),
            resources.getString(R.string.choose_theme_seven),
            resources.getString(R.string.choose_theme_eight),
            resources.getString(R.string.choose_theme_nine),
            resources.getString(R.string.choose_theme_ten)
        )

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

        var selectedItems: String
        //val listOfSelectedItems = arrayListOf<Int>()

        listView.onItemClickListener =
            OnItemClickListener { arg0, arg1, arg2, arg3 -> //List list = new ArrayList();
                selectedItems = "Selected Items"
                listOfSelectedItems.clear() // clear former variables
                val a: SparseBooleanArray = listView.checkedItemPositions
                for (i in themeList.indices) {
                    if (a.get(i)) {
                        Log.i("ValuesDebug: ", i.toString())
                        selectedItems = (selectedItems + ","
                                + listView.adapter.getItem(i) as String)
                        listOfSelectedItems.add(i + 1)
                    }
                }
                Log.v("values", selectedItems)
            }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun startActivity(chapters: Int, mode: Int) {
        val intent = Intent(this, Theory::class.java)
        intent.putExtra("Theme", mode)
        intent.putExtra("Chapters", chapters)
        startActivity(intent)
    }

    @Suppress("UNUSED_PARAMETER")
    fun startLearning(view: View) {

        var tmp = ""
        for (i in listOfSelectedItems) {
            tmp += i
        }
        if (tmp != "") {

            if (examModeBool) {
                Log.i("values", tmp)
                startActivity(tmp.toInt(), -4)
            }

            if (rndModeBool) {
                startActivity(tmp.toInt(), -5)
            }

            //Three States
            /*
            *  0 = Learning normal
            *  1 = Zufall (all questions)
            *  3 = exam random 40 questions
            * */
        }
    }


}