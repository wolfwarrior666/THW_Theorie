package de.wolfwarrior.thwtheorie.logik

import Question
import android.util.Log

interface TheorieLogikInterface {
    fun initData(questions: List<Question>, learnState: HashMap<String, Int>)
    fun getLearnSetQuestion(): Question
    fun getResults(): String
    fun hasNextQuestion(): Boolean

    fun loadData(chapterNumber: Int)

    fun checkAnswers(answer: String): Boolean

    fun getThemeID(): Int

    fun getPercentage(): Int
    fun  getLearnState():String
}









