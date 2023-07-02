package de.wolfwarrior.thwtheorie

import Question
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.wolfwarrior.thwtheorie.datastructures.testData
import de.wolfwarrior.thwtheorie.logik.TheorieLogik
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class Theory : AppCompatActivity() {
    private var theme: Int = 0; //Welches Thema gelernt werden soll
    lateinit var model: TheorieLogik //Model
    lateinit var question: Question //Aktuelle Frage aus dem Model
    var correctnesCheck = false

    //UIElemente
    lateinit var answerA: CheckBox
    lateinit var answerB: CheckBox
    lateinit var answerC: CheckBox
    lateinit var questionText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)

        val theme = intent.getIntExtra("Theme", -1)

        model = TheorieLogik(loadQuestionsData(), loadLearnState())
        model.loadDataFromOneChapter(theme)
        question = model.getLearnSetQuestion()

        questionText = findViewById<TextView>(R.id.theory_question_text)

        answerA = findViewById<CheckBox>(R.id.theroy_answer_a)

        answerB = findViewById<CheckBox>(R.id.theroy_answer_b)

        answerC = findViewById<CheckBox>(R.id.theroy_answer_c)

        nextQuestion()

    }

    fun loadQuestionsData(): List<Question> {
        val tmp = Json.decodeFromString<List<Question>>(testData)
        return tmp
    }

    fun loadLearnState(): HashMap<String, Int> {
        return HashMap<String, Int>()
    }


    fun button(view: View) {
        if(correctnesCheck){
            nextQuestion()
        }else{
            checkForCorrectness()
        }
        correctnesCheck = !correctnesCheck
    }

    /*if(answerA.isChecked){
        answerA.setBackgroundColor(Color.RED)
    }else{
        answerA.setBackgroundColor(Color.TRANSPARENT)
    }*/
    //Toast.makeText(this, "Cheked A:"+answerA.isChecked.toString(), Toast.LENGTH_LONG).show();
    fun colourResults() {
        //Was passiert wenn es falsch ist
        if (answerA.isChecked == question.answerA.rightOrWrong) {
            if (question.answerA.rightOrWrong) {
                answerA.setBackgroundColor(Color.GREEN) //Wenn alles Richtig ist Grün
            } else {
                answerA.setBackgroundColor(Color.TRANSPARENT) //Wenn antwort wie gewüsncht nciht gewählt wurde
            }
        } else {
            if (question.answerA.rightOrWrong) {
                answerA.setBackgroundColor(Color.GREEN) //Antwort war nicht makriert sollte es aber sein
            } else {
                answerA.setBackgroundColor(Color.RED) //Antwort war falsch makiert
            }
        }

        if (answerB.isChecked == question.answerB.rightOrWrong) {
            if (question.answerB.rightOrWrong) {
                answerB.setBackgroundColor(Color.GREEN)
            } else {
                answerB.setBackgroundColor(Color.TRANSPARENT)
            }
        } else {
            if (question.answerB.rightOrWrong) {
                answerB.setBackgroundColor(Color.GREEN)
            } else {
                answerB.setBackgroundColor(Color.RED)
            }
        }

        if (answerC.isChecked == question.answerC.rightOrWrong) {
            if (question.answerC.rightOrWrong) {
                answerC.setBackgroundColor(Color.GREEN)
            } else {
                answerC.setBackgroundColor(Color.TRANSPARENT)
            }
        } else {
            if (question.answerC.rightOrWrong) {
                answerC.setBackgroundColor(Color.GREEN)
            } else {
                answerC.setBackgroundColor(Color.RED)
            }
        }
    }

    fun nextQuestion(){
        question = model.getLearnSetQuestion() //Bekomme neue Frage
        //Setze Bildschirm Zurück
        answerA.text = question.answerA.answer.trimIndent()
        answerB.text = question.answerB.answer.trimIndent()
        answerC.text = question.answerC.answer.trimIndent()
        questionText.text = question.question.trimIndent()

        answerA.setBackgroundColor(Color.TRANSPARENT)
        answerB.setBackgroundColor(Color.TRANSPARENT)
        answerC.setBackgroundColor(Color.TRANSPARENT)

        answerA.isChecked = false
        answerB.isChecked = false
        answerC.isChecked = false
    }

    fun checkForCorrectness(){
        var result = ""
        Log.i("THWTheroy", "A " + answerA.isChecked.toString())
        Log.i("THWTheroy", "B " + answerB.isChecked.toString())
        Log.i("THWTheroy", "C " + answerC.isChecked.toString())
        if (answerA.isChecked) {
            result += "A"
        }

        if (answerB.isChecked) {
            result += "B"
        }

        if (answerC.isChecked) {
            result += "C"
        }

        if (model.checkAwnsers(result)) {
            Log.i("THWTheroy", "Das war richtig :D!!!!")
            colourResults()
        } else {
            colourResults()
        }
    }
}



