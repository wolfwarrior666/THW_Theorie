package de.wolfwarrior.thwtheorie

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TheroyTestLearnResults : AppCompatActivity() {
    private var countofall:Int = 0
    private var rightanswerd:Int=0
    private var wronganswerd:Int=0
    private var themeID:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theroy_test_learn_results)

        countofall = intent.getIntExtra("all",-1)
        rightanswerd = intent.getIntExtra("right",-1)
        wronganswerd = intent.getIntExtra("wrong",-1)
        themeID = intent.getIntExtra("ThemeID",-1)
        val btn = findViewById<TextView>(R.id.theroy_results_all)
        btn.text = countofall.toString()

        val text = "Gratulation du hast den Themenabschnitt $themeID erfolgreich durchlaufen. Dabei hast du $countofall Fragen beantwortet, davon waren $rightanswerd richtig und $wronganswerd falsch."
        btn.text = text
    }
}