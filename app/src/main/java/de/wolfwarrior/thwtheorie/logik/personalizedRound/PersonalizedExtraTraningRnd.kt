package de.wolfwarrior.thwtheorie.logik.personalizedRound

import Question
import de.wolfwarrior.thwtheorie.logik.GeneralImplementationK
import de.wolfwarrior.thwtheorie.logik.TheorieLogikInterface
import java.util.Random

class PersonalizedExtraTraningRnd : GeneralImplementationK(), TheorieLogikInterface {
    override fun getResults(): String {
        val questionCounts = wrong + right
        return "Gratulation du hast $questionCounts Fragen, in zufälliger Reihenfolge erfolgreich beantwortet. Dabei hast du $questionCounts Fragen beantwortet, davon waren $right richtig und $wrong falsch."

    }

    override fun loadData(chapterNumber: Int) {
        val chaptersList = arrayListOf<Int>()
        val chapters = chapterNumber.toString()
        val tmpQuestionList = mutableListOf<Question>()

        //Check if Chapter 10 is included
        if (chapters.last() == '0') {
            chaptersList.add(10)
            chapters.replace("10", "")
        }

        //Collect all other chapters
        val chaptersCharArry = chapters.toCharArray()
        //Collect all other chapters
        for(i in chaptersCharArry){
            chaptersList.add(i.digitToInt())
        }


        // ResetValues
        themeID = chapterNumber
        currentLearnSet.clear()
        currentIndex = 0
        right = 0
        wrong = 0


        //StartLoadingData
        for (q in questions) {
            //if (q.questionID.contains("$chapterNumber.")) {
            //    currentLearnSet.add(q)
            //}
            for (i in chaptersList) {
                if (q.questionID.contains("$i.")) {
                    tmpQuestionList.add(q)
                }
            }

        }

        //List of all usableQuestion now choose 40 of them

        var tmpId: Int

        val rnd = Random()
        while (tmpQuestionList.isNotEmpty()) {
            tmpId = rnd.nextInt(tmpQuestionList.size)
            currentLearnSet.add(tmpQuestionList[tmpId])
            tmpQuestionList.removeAt(tmpId)
        }
    }
}