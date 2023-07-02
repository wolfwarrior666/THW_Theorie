package de.wolfwarrior.thwtheorie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ChooseTheme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_theme)
    }

    fun themeOn(view:View){
        var intent = Intent(this,Theory::class.java)
        intent.putExtra("Theme",1)
        startActivity(intent)
    }
}