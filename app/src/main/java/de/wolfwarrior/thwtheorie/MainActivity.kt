package de.wolfwarrior.thwtheorie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.serialization.json.Json


@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {
    private val questionnaires: List<String> = listOf("Fragebogen: 2022","2023","2024")
    private lateinit var spinner:Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        val toolbar = findViewById<MaterialToolbar>(R.id.main_menu_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,questionnaires)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner = findViewById(R.id.main_menu_spinner)
        spinner.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.thw_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.showgithub -> {
                showGitHub()
                true
            }

            R.id.aboutthisapp -> {
                aboutThisApp()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("ApplySharedPref")
    fun getSelectedQuestionnaire(view: View){
        var selectedItem =  spinner.selectedItem.toString()

        if (selectedItem == "Fragebogen: 2022"){
            selectedItem = "questions_2022_3_4"
        }


        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("selected_questionnaire", selectedItem)
        editor.commit()

    }

    fun showList(view: View) {
        getSelectedQuestionnaire(view)
        startActivity(Intent(this, ChooseTheme::class.java))
    }

    @Suppress("UNUSED_PARAMETER")
    fun startExam(view: View) {
        val intent = Intent(this, Theory::class.java)
        intent.putExtra("Theme", -2)
        startActivity(intent)
    }

    @Suppress("UNUSED_PARAMETER")
    fun openPersonalizedLearnSettings(view: View) {
        val intent = Intent(this, PersonalizeNextRound::class.java)
        intent.putExtra("Theme", -2)
        startActivity(intent)
    }


    @Suppress("UNUSED_PARAMETER")
    fun startExtraTraining(view: View) {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val questionnaire = sharedPreference.getString("selected_questionnaire", "null").toString()
        val learnStateString = sharedPreference.getString(questionnaire, "null")

        if (learnStateString.equals("null")) {
            Toast.makeText(this, R.string.main_menu_toast_error, Toast.LENGTH_LONG).show()
        } else {
            val learnState =
                Json.decodeFromString(learnStateString.toString()) as HashMap<String, Int>
            val tmpIDS = ArrayList<String>()

            for ((key, value) in learnState) {
                if (value == 0) {
                    tmpIDS.add(key)
                }
            }

            if (tmpIDS.isNotEmpty()) {
                val intent = Intent(this, Theory::class.java)
                intent.putExtra("Theme", -3)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.main_menu_toast_error, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun aboutThisApp() {
        val url = "https://github.com/wolfwarrior666/THW_Theorie#thw_theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun showGitHub() {
        val url = "https://github.com/wolfwarrior666/THW_Theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}