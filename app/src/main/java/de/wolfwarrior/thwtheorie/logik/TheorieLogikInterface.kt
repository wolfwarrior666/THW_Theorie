package de.wolfwarrior.thwtheorie.logik

import Question
import android.util.Log

interface TheorieLogikInterface{

    fun getLearnSetQuestion(): Question
    fun getResults(): MutableMap<String, Int>
    fun hasNextQuestion():Boolean
}



open class TmpNameClass {

    internal var currentLearnSet = mutableListOf<Question>()
    internal var currentIndex = 0
    internal lateinit var currentQuestion:Question
    internal var wrong=0
    internal var right=0
    private var themeID = -1
    private lateinit var questions:List<Question>
    private lateinit var learnState:HashMap<String,Int>

 //Init persistence data
    fun initData(questions:List<Question>, learnState:HashMap<String,Int>){
     this.questions = questions
     this.learnState = learnState
 }
    fun loadDataFromOneChapter(chapterNumber: Int) {
        themeID = chapterNumber
        currentLearnSet.clear()
        currentIndex = 0
        right = 0
        wrong = 0
        for (q in questions) {
            if (q.questionID.contains("$chapterNumber.")) {
                currentLearnSet.add(q)
            }
        }
    }

    fun checkAnswers(answer: String):Boolean {
        var correct = true
        if (answer.contains("A") == currentQuestion.answerA.rightOrWrong) {
            Log.i("THWTheory", "Answer A is correct")
        }else{
            correct = false
        }

        if (answer.contains("B") == currentQuestion.answerB.rightOrWrong) {
            Log.i("THWTheory", "Answer B is correct")
        }else{
            correct = false
        }

        if (answer.contains("C") == currentQuestion.answerC.rightOrWrong){
            Log.i("THWTheory", "Answer C is correct")
        }else{
            correct = false
        }

        if (correct){
            right++
            if (learnState[currentQuestion.questionID] != null){
                learnState[currentQuestion.questionID] = learnState[currentQuestion.questionID]!! + 1
            }else{
                learnState[currentQuestion.questionID] = 1
            }
        }else{
            wrong++
        }
        return correct
    }

    fun getThemeID():Int{
        return themeID
    }
}