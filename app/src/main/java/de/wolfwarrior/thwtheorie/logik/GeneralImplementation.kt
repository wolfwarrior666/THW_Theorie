package de.wolfwarrior.thwtheorie.logik


import Question
import android.util.Log
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class GeneralImplementationK : TheorieLogikInterface {
    internal var currentLearnSet = mutableListOf<Question>()
    var currentIndex: Int = 0
    private lateinit var currentQuestion: Question
    var wrong: Int = 0
    var right: Int = 0
    internal var themeID = -1
    internal lateinit var questions: List<Question>
    internal lateinit var learnState: HashMap<String, Int>
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
        if (currentIndex == currentLearnSet.size) {
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


        /*
        This Parts Adds the ID of an Question to the learnstate DateStructure.
        By this the Datastructure Counts the amount of following right checked amwsers.
         */
        if (correct) {
            right++
            if (learnState[currentQuestion.questionID] != null) { //Check if the id allready exist
                learnState[currentQuestion.questionID] =
                    learnState[currentQuestion.questionID]!! + 1
            } else {
                learnState[currentQuestion.questionID] =
                    1 //If the idea does not exist then create it
            }
        } else {
            wrong++
            if (learnState[currentQuestion.questionID] != null) { //Checks if the id  already exist in the Structure
                learnState[currentQuestion.questionID] =
                    0 //If Question was already correct answered but now wrong -> set counter to zero
            }

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

    override fun getLearnState(): String {
        return Json.encodeToString(learnState)

    }

}






