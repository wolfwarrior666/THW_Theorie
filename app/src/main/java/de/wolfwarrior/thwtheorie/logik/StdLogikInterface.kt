package de.wolfwarrior.thwtheorie.logik

import Question
import android.util.Log

class StdLogikInterface : TheorieLogikInterface{
    private var currentLearnSet = mutableListOf<Question>()
    private var currentIndex = 0
    private lateinit var currentQuestion:Question
    private var wrong=0
    private var right=0
    private var themeID = -1
    private lateinit var questions:List<Question>
    private lateinit var learnState:HashMap<String,Int>
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
        return "Gratulation du hast den Themenabschnitt $themeID erfolgreich durchlaufen. Dabei hast du $questionCounts Fragen beantwortet, davon waren $right richtig und $wrong falsch."
    }

    override fun hasNextQuestion(): Boolean {
        if(currentIndex == currentLearnSet.size){
            return false
        }
        return true
    }

    override fun loadData(chapterNumber: Int) {
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

    override fun checkAnswers(answer: String): Boolean {
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

    override fun getThemeID(): Int {
        return themeID
    }

}