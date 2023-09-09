package de.wolfwarrior.thwtheorie.logik

import Question
import android.util.Log
import java.util.Random

class MokExamLogik : TheorieLogikInterface {
    private var currentLearnSet = mutableListOf<Question>()
    private var currentIndex = 0
    private lateinit var currentQuestion: Question
    private var wrong = 0
    private var right = 0
    private var themeID = -2
    private lateinit var questions: List<Question>
    private lateinit var learnState: HashMap<String, Int>
    override fun initData(questions: List<Question>, learnState: HashMap<String, Int>) {
        this.questions = questions
        this.learnState = learnState
    }

    override fun getLearnSetQuestion(): Question {
        currentQuestion = currentLearnSet[currentIndex]
        currentIndex++
        return currentQuestion
    }

    override fun getResults(): String {
        val questionCounts = wrong + right
        return "Gratulation du den Test erfolgreich durchlaufen. Dabei hast du $questionCounts Fragen beantwortet, davon waren $right richtig und $wrong falsch."

    }

    override fun hasNextQuestion(): Boolean {
        if (currentIndex == currentLearnSet.size) {
            return false
        }
        return true
    }

    /*
    Choose random 40 Questions
     */
    override fun loadData(chapterNumber: Int) {
        currentLearnSet.clear()
        currentIndex = 0
        right = 0
        wrong = 0
        val tmpIdList = ArrayList<String>()

        val rnd = Random()
        for (i in (0 until 40)) {
            var check = true
            while (check) {
                val index = rnd.nextInt(questions.size)

                if (!tmpIdList.contains(questions[index].questionID)) {
                    currentLearnSet.add(questions[index])
                    tmpIdList.add(questions[index].questionID)
                    check = false
                }
            }
        }
    }

    override fun checkAnswers(answer: String): Boolean {
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

    override fun getThemeID(): Int {
        return themeID
    }

    override fun getPercentage(): Int {
        val a = ((currentIndex.toDouble()) / currentLearnSet.size.toDouble()) * 100.00
        return a.toInt()
    }


}