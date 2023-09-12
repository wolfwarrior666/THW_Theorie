package de.wolfwarrior.thwtheorie.logik

/**
 * ExtraTraining chose 20 Question which are not learned yet or wrong answered at last
 */
class ExtraTraining: GeneralImplementation(), TheorieLogikInterface{
    override fun loadData(chapterNumber: Int) {
       // learnState
       // currentLearnSet
       // questions
        currentLearnSet.clear()
        currentIndex = 0
        //Collect all Wrong Answered Questions
        val tmpIDS = ArrayList<String>()
        for (x in learnState){
            if(x.value == 0){
                tmpIDS.add(x.key)
            }
        }

        if (tmpIDS.isNotEmpty()){
            for (x in questions){
                if(tmpIDS.contains(x.questionID)){
                    currentLearnSet.add(x)
                }
            }
        }

    }

    override fun getResults(): String {
        return "Ende"
    }
}