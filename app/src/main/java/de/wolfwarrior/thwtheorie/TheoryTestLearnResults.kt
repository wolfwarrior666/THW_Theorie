package de.wolfwarrior.thwtheorie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TheoryTestLearnResults : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theroy_test_learn_results)

        val resultText: String? = intent.getStringExtra("test")
        val btn = findViewById<TextView>(R.id.theroy_results_all)

        btn.text = resultText
    }
}