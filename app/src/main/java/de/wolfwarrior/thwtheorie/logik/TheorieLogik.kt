package de.wolfwarrior.thwtheorie.logik

import Question
import android.util.Log
import java.io.File

private var currentLearnSet = mutableListOf<Question>()
private var currentIndex = 0;
private lateinit var currentQuestion:Question

class TheorieLogik (val questions:List<Question>, val learnState:HashMap<String,Int>) {
    fun loadDataFromOneChapter(chapterNumber: Int) {
        currentLearnSet.clear()
        currentIndex = 0;
        for (q in questions) {
            if (q.questionID.contains("$chapterNumber.")) {
                currentLearnSet.add(q)
            }
        }
    }

    fun getLearnSetQuestion(): Question {
        val currentQuestion = currentLearnSet[currentIndex];
        currentIndex++
        return currentQuestion;
    }

    fun checkAwnsers(answer: String):Boolean {
       var correct = true;
        if (answer.contains("A") == currentQuestion.answerA.rightOrWrong) {
            Log.i("THWTheroy", "Answer A is correct")
        }else{
            correct = false
        }

        if (answer.contains("B") == currentQuestion.answerB.rightOrWrong) {
            Log.i("THWTheroy", "Answer B is correct")
        }else{
            correct = false
        }

        if (answer.contains("C") == currentQuestion.answerC.rightOrWrong){
            Log.i("THWTheroy", "Answer C is correct")
        }else{
            correct = false
        }

        if (correct){
            if (learnState[currentQuestion.questionID] != null){
                learnState[currentQuestion.questionID] = learnState[currentQuestion.questionID]!! + 1
            }else{
                learnState[currentQuestion.questionID] = 1
            }
        }
        return correct
    }
}