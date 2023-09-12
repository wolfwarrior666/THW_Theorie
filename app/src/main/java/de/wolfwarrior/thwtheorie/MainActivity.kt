package de.wolfwarrior.thwtheorie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.main_menu_toolbar)
        setSupportActionBar(toolbar)
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


    @Suppress("UNUSED_PARAMETER")
    fun showList(view: View) {
        startActivity(Intent(this, ChooseTheme::class.java))
    }

    @Suppress("UNUSED_PARAMETER")
    fun startExam(view: View){
        val intent = Intent(this,Theory::class.java)
        intent.putExtra("Theme",-2)
        startActivity(intent)
    }


    @Suppress("UNUSED_PARAMETER")
    fun startExtraTraining(view: View) {

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var learnStateString = sharedPreference.getString("learnstate", "null")

        if (learnStateString.equals("null")) {
            Toast.makeText(this, R.string.main_menu_toast_error, Toast.LENGTH_LONG).show()
        } else {
            val learnState = Json.decodeFromString(learnStateString.toString()) as HashMap<String,Int>
            val tmpIDS = ArrayList<String>()

            for (x in learnState) {
                if (x.value == 0) {
                    tmpIDS.add(x.key)
                }
            }

            if (tmpIDS.isNotEmpty()) {
                val intent = Intent(this, Theory::class.java)
                intent.putExtra("Theme", -3)
                startActivity(intent)
            }
            Toast.makeText(this, R.string.main_menu_toast_error, Toast.LENGTH_LONG).show()
        }


    }


    private fun aboutThisApp(){
        val url = "https://github.com/wolfwarrior666/THW_Theorie#thw_theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun showGitHub(){
        val url = "https://github.com/wolfwarrior666/THW_Theorie"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}