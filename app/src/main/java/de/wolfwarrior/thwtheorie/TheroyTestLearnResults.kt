package de.wolfwarrior.thwtheorie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TheroyTestLearnResults : AppCompatActivity() {
    var countofall:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theroy_test_learn_results)

        countofall = intent.getIntExtra("all",-1)
        var btn = findViewById<TextView>(R.id.theroy_results_all)
        btn.text = countofall.toString()

    }
}