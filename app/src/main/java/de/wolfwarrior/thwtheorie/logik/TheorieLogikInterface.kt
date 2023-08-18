package de.wolfwarrior.thwtheorie.logik

import Question

interface TheorieLogikInterface {

    fun getLearnSetQuestion(): Question
    fun getResults(): MutableMap<String, Int>
    fun hasNextQuestion():Boolean
    fun getThemeID():Int
}