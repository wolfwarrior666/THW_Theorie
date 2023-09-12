package de.wolfwarrior.thwtheorie.logik.old

import Question
import android.util.Log

private var currentLearnSet = mutableListOf<Question>()
private var currentIndex = 0
private lateinit var currentQuestion: Question
private var wrong = 0
private var right = 0
private var themeID = -1

@Suppress("unused")
class TheorieLogik(
    private val questions: List<Question>,
    private val learnState: HashMap<String, Int>
) {
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

    fun getLearnSetQuestion(): Question {
        currentQuestion = currentLearnSet[currentIndex]
        currentIndex++
        return currentQuestion
    }

    fun checkAnswers(answer: String): Boolean {
        var correct = true
        if (answer.contains("A") == currentQuestion.answerA.rightOrWrong) {
            Log.i("THWTheory", "Answer A is correct")
        } else {
            correct = false
        }

        if (answer.contains("B") == currentQuestion.answerB.rightOrWrong) {
            Log.i("THWTheory", "Answer B is correct")
        } else {
            correct = false
        }

        if (answer.contains("C") == currentQuestion.answerC.rightOrWrong) {
            Log.i("THWTheory", "Answer C is correct")
        } else {
            correct = false
        }

        if (correct) {
            right++
            if (learnState[currentQuestion.questionID] != null) {
                learnState[currentQuestion.questionID] =
                    learnState[currentQuestion.questionID]!! + 1
            } else {
                learnState[currentQuestion.questionID] = 1
            }
        } else {
            wrong++
        }
        return correct
    }

    fun getResults(): MutableMap<String, Int> {
        val result = mutableMapOf<String, Int>()
        result["questions"] = wrong + right
        result["right"] = right
        result["wrong"] = wrong
        return result
    }

    fun hasNextQuestion(): Boolean {
        if (currentIndex == currentLearnSet.size) {
            return false
        }
        return true
    }

    fun getThemeID(): Int {
        return themeID
    }
}

