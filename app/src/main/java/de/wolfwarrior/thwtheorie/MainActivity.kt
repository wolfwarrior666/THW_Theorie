package de.wolfwarrior.thwtheorie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

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

    @Suppress("UNUSED_PARAMETER")
    fun showList(view: View) {
        startActivity(Intent(this, ChooseTheme::class.java))
    }

    @Suppress("UNUSED_PARAMETER")
    fun startExam(view: View){
        var intent = Intent(this,Theory::class.java)
        intent.putExtra("Theme",-2)
        startActivity(intent)
    }
}