package de.wolfwarrior.thwtheorie.logik

/**
 * ExtraTraining chose 20 Question which are not learned yet or wrong answered at last
 */
class ExtraTrainingLogik : GeneralImplementationK(), TheorieLogikInterface {
    override fun loadData(chapterNumber: Int) {
        // learnState
        // currentLearnSet
        // questions
        currentLearnSet.clear()
        currentIndex = 0
        //Collect all Wrong Answered Questions
        val tmpIDS = ArrayList<String>()
        for ((key, value) in learnState) {
            if (value == 0) {
                tmpIDS.add(key)
            }
        }

        if (tmpIDS.isNotEmpty()) {
            for (x in questions) {
                if (tmpIDS.contains(x.questionID)) {
                    currentLearnSet.add(x)
                }
            }
        }

    }

    override fun getResults(): String {
        val questionCounts = wrong + right
        return "Gratulation du hast versucht deine $questionCounts Fehler auszubessern. Dabei warst du bei $right erfolgreich. Somit musst du nur noch $wrong Fehler ausbessern. "
    }
}