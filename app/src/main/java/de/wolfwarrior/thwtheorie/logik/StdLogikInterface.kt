package de.wolfwarrior.thwtheorie.logik

import Question

class StdLogikInterface: TheorieLogikInterface,TmpNameClass() {
    override fun getLearnSetQuestion(): Question {
        TODO("Not yet implemented")
        currentQuestion = currentLearnSet[currentIndex]
        currentIndex++
        return currentQuestion
    }

    override fun getResults(): MutableMap<String, Int> {
        val result = mutableMapOf<String, Int>()
        result["questions"] = wrong + right
        result["right"] = right
        result["wrong"] = wrong
        return result
    }

    override fun hasNextQuestion(): Boolean {
        if(currentIndex == currentLearnSet.size){
            return false
        }
        return true
    }
}