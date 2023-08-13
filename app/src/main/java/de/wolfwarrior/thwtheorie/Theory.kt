package de.wolfwarrior.thwtheorie

import Question
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.wolfwarrior.thwtheorie.datastructures.testData
import de.wolfwarrior.thwtheorie.logik.TheorieLogik
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


@Suppress("MemberVisibilityCanBePrivate")
class Theory : AppCompatActivity() {
    private var theme: Int = 0 //Welches Thema gelernt werden soll
    private lateinit var model: TheorieLogik //Model
    private lateinit var question: Question //Aktuelle Frage aus dem Model
    private var correctCheck = false

    //UIElemente
    private lateinit var answerA: CheckBox
    private lateinit var answerB: CheckBox
    private lateinit var answerC: CheckBox
    private lateinit var questionText: TextView
    private lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)

        val theme = intent.getIntExtra("Theme", -1)

        model = TheorieLogik(loadQuestionsData(), loadLearnState())
        model.loadDataFromOneChapter(theme)

        questionText = findViewById(R.id.theory_question_text)

        answerA = findViewById(R.id.theroy_answer_a)

        answerB = findViewById(R.id.theroy_answer_b)

        answerC = findViewById(R.id.theroy_answer_c)

        button = findViewById(R.id.theroy_function)

        nextQuestion()

    }

    fun loadQuestionsData(): List<Question> {
        val test =  resources.openRawResource(R.raw.questions_2022).bufferedReader().use { it.readText() }//R.raw.questions_2022
        //println(test)
        //return Json.decodeFromString(testData)
        return Json.decodeFromString(test)

    }

    fun loadLearnState(): HashMap<String, Int> {
        return HashMap()
    }


    @Suppress("UNUSED_PARAMETER")
    fun button(view:View) {
        if(correctCheck){
            if(model.hasNextQuestion()){
                nextQuestion()
                button.text = getString(R.string.theroy_check)
            }else{
                showResults() // Springt zur nächsten Activity to show the Results of the Learning success
            }
        }else{
            checkForCorrectness()
            button.text = getString(R.string.theroy_next)

        }
        correctCheck = !correctCheck
    }

    /*if(answerA.isChecked){
        answerA.setBackgroundColor(Color.RED)
    }else{
        answerA.setBackgroundColor(Color.TRANSPARENT)
    }*/
    //Toast.makeText(this, "Checked A:"+answerA.isChecked.toString(), Toast.LENGTH_LONG).show();
    fun colourResults() {
        //Was passiert wenn es falsch ist
        if (answerA.isChecked == question.answerA.rightOrWrong) {
            if (question.answerA.rightOrWrong) {
                answerA.setBackgroundColor(Color.GREEN) //Wenn alles Richtig ist Grün
            } else {
                answerA.setBackgroundColor(Color.TRANSPARENT) //Wenn antwort wie gewünscht nicht gewählt wurde
            }
        } else {
            if (question.answerA.rightOrWrong) {
                answerA.setBackgroundColor(Color.GREEN) //Antwort war nicht markiert sollte es aber sein
            } else {
                answerA.setBackgroundColor(Color.RED) //Antwort war falsch markiert
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
        Log.i("THWTheory", "A " + answerA.isChecked.toString())
        Log.i("THWTheory", "B " + answerB.isChecked.toString())
        Log.i("THWTheory", "C " + answerC.isChecked.toString())
        if (answerA.isChecked) {
            result += "A"
        }

        if (answerB.isChecked) {
            result += "B"
        }

        if (answerC.isChecked) {
            result += "C"
        }

        if (model.checkAnswers(result)) {
            Log.i("THWTheory", "Das war richtig :D!!!!")
            colourResults()
        } else {
            colourResults()
        }
    }

    fun showResults(){
        val results = model.getResults()
        val intent = Intent(this, TheroyTestLearnResults::class.java)
        intent.putExtra("all",results["questions"])
        intent.putExtra("right",results["right"])
        intent.putExtra("wrong",results["wrong"])
        intent.putExtra("ThemeID",theme)

        startActivity(intent)
    }
}



