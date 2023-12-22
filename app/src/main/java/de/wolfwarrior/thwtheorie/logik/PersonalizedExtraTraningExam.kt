package de.wolfwarrior.thwtheorie.logik

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class PersonalizedExtraTraningExam :GeneralImplementationK(), TheorieLogikInterface{
    override fun getResults(): String {
        return super.getResults()
    }

    override fun loadData(chapterNumber: Int) {
        var chaptersList = arrayListOf<Int>()
        val chapters = chapterNumber.toString()

        //Check if Chapter 10 is included
        if(chapters.last() == '0'){
            chaptersList.add(10)
            chapters.replace("10","")
        }

        //Collect all other chapters
        for(i in (chapters.indices)){
            chaptersList.add(i)
        }
        //StartLoadinggData



        super.loadData(chapterNumber)
    }
}