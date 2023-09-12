package de.wolfwarrior.thwtheorie


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.wolfwarrior.thwtheorie.datastructures.Question
import de.wolfwarrior.thwtheorie.logik.ExtraTrainingLogik
import de.wolfwarrior.thwtheorie.logik.MokExamLogik
import de.wolfwarrior.thwtheorie.logik.StdLogik
import de.wolfwarrior.thwtheorie.logik.TheorieLogikInterface
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


@Suppress("MemberVisibilityCanBePrivate")
class Theory : AppCompatActivity() {
    private lateinit var model: TheorieLogikInterface //Model
    private lateinit var question: Question //Aktuelle Frage aus dem Model
    private var correctCheck = false
    private var onCreated = false

    //UIElemente
    private lateinit var answerA: CheckBox
    private lateinit var answerB: CheckBox
    private lateinit var answerC: CheckBox
    private lateinit var questionText: TextView
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)

        val theme = intent.getIntExtra("Theme", -1)

        model = when (theme) {
            -2 -> MokExamLogik()
            -3 -> ExtraTrainingLogik()
            else -> {
                StdLogik()
            }
        }
        model.initData(loadQuestionsData(), loadLearnState()) //Init Data
        model.loadData(theme)


        questionText = findViewById(R.id.theory_question_text)

        answerA = findViewById(R.id.theroy_answer_a)

        answerB = findViewById(R.id.theroy_answer_b)

        answerC = findViewById(R.id.theroy_answer_c)

        button = findViewById(R.id.theroy_function)

        progressBar = findViewById(R.id.theory_statusbar)

        nextQuestion()

        onCreated = true //BugFix

    }

    fun loadQuestionsData(): List<Question> {
        val test = resources.openRawResource(R.raw.questions_2022).bufferedReader()
            .use { it.readText() }//R.raw.questions_2022

        return Json.decodeFromString(test)

    }

    fun loadLearnState(): HashMap<String, Int> {
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val learnState = sharedPreference.getString("learnstate", "null")
        if (learnState.equals("null")) {
            return HashMap()
        }
        return Json.decodeFromString(learnState.toString())
    }


    @Suppress("UNUSED_PARAMETER")
    fun button(view: View) {
        if (correctCheck) {
            if (model.hasNextQuestion()) {
                nextQuestion()
                button.text = getString(R.string.theroy_check)
            } else {
                showResults() // Springt zur nächsten Activity to show the Results of the Learning success
            }
        } else {
            checkForCorrectness()
            button.text = getString(R.string.theroy_next)

        }
        correctCheck = !correctCheck
    }

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

    fun nextQuestion() {
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

        adjustProgressBar()

    }


    fun adjustProgressBar() {
        val tmp = model.getPercentage()
        progressBar.progress = tmp
    }

    fun checkForCorrectness() {
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

    fun showResults() {
        val results = model.getResults()
        val intent = Intent(this, TheoryTestLearnResults::class.java)
        intent.putExtra("test", results)
        val tmp = model.getLearnState()
        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("learnstate", tmp)
        editor.apply()
        startActivity(intent)
    }


    override fun onResume() {
        //If there is no other Question to do the Activity will close
        // immediately and will bring the user back to the LearnAbschnitt Activity
        if (!model.hasNextQuestion() && !onCreated) {
            finish()
        }
        onCreated = false
        super.onResume()
    }
}



