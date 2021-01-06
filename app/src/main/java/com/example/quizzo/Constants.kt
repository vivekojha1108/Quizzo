package com.example.quizzo

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String ="correct_answers"


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "What Country does this flag belongs to?",
            R.drawable.india_flag,
            "India",
            "America",
            "UK",
            "Bangladesh",
            1
        )
        questionsList.add(que1)

        val que2 = Question(
            2,
            "What Country does this flag belongs to?",
            R.drawable.uk_flag,
            "India",
            "UK",
            "Bangladesh",
            "America",
            2
        )
        questionsList.add(que2)

        val que3 = Question(
            3,
            "what Country does this flag belongs to?",
            R.drawable.bangladesh_flag,
            "India",
            "Bangladesh",
            "America",
            "UK",
            2
        )
        questionsList.add(que3)

        val que4 = Question(
            4,
            "what Country does this flag belongs to?",
            R.drawable.america_flag,
            "India",
            "UK",
            "America",
            "Bangladesh",
            3
        )
        questionsList.add(que4)

        val que5 = Question(
            5,
            "what Country does this flag belongs to?",
            R.drawable.canada_flag,
            "India",
            "America",
            "UK",
            "Non of These",
            4
        )
        questionsList.add(que5)


        return questionsList
    }
}