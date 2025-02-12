package com.berkson.quizapp.utils

import com.berkson.quizapp.R
import com.berkson.quizapp.model.Question

object Constants {

    fun getQuestions(): MutableList<Question> {
        val question = "A que País esta bandeira pertence?"
        val questions = mutableListOf<Question>()

        val quest1 = Question(
            1, question,
            R.drawable.italy_flag, "Itália", "India", "Irã",
            "Irlanda", 1
        )

        questions.add(quest1)

        val quest2 = Question(
            2, question,
            R.drawable.argentina_flag,
            "Armênia", "Argentina",
            "Austria", "Austrália",
            2
        )
        questions.add(quest2)

        val quest3 = Question(
            3, question,
            R.drawable.brazil_flag,
            "Belarus", "Bélgica",
            "Bangladesh", "Brasil",
            4
        )
        questions.add(quest3)

        val quest4 = Question(
            4, question,
            R.drawable.france_flag,
            "Finlândia", "Fiji",
            "França", "Nenhuma das opções",
            3
        )
        questions.add(quest4)

        val quest5 = Question(
            5, question,
            R.drawable.finland_flag,
            "Finland", "Fiji",
            "France", "Nenhuma das anteriores",
            1
        )
        questions.add(quest5)

        val quest6 = Question(
            6, question,
            R.drawable.germany_flag,
            "Gambia", "Alemanha",
            "Georgia", "Grecia",
            2
        )
        questions.add(quest6)

        val quest7 = Question(
            7, question,
            R.drawable.nigeria_flag,
            "Holanda", "Nicaragua",
            "Nigéria", "Nepal",
            3
        )
        questions.add(quest7)

        val quest8 = Question(
            8, question,
            R.drawable.romania_flag,
            "Russia", "Rwanda",
            "Nenhuma das anteriores", "Romênia",
            4
        )
        questions.add(quest8)

        val quest9 = Question(
            9, question,
            R.drawable.spain_flag,
            "Servia", "Espanha",
            "Arábia Saudita", "Eslovênia",
            2
        )
        questions.add(quest9)

        val quest10 = Question(
            10, question,
            R.drawable.haiti_flag,
            "Honduras", "Hungary",
            "Haiti", "None of the options",
            3
        )
        questions.add(quest10)

        return questions;
    }
}