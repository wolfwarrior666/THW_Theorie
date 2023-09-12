package de.wolfwarrior.thwtheorie.datastructures
import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val questionID:String,
    val question:String,
    val answerA: Answer,
    val answerB: Answer,
    val answerC: Answer,
)

@Serializable
data class Answer (
        val answer:String,
        val rightOrWrong:Boolean,
        )