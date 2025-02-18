package com.berkson.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.berkson.quizapp.R
import com.berkson.quizapp.model.Question
import com.berkson.quizapp.utils.Constants
import com.google.android.material.button.MaterialButton

class QuestionsActivity : AppCompatActivity(), OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var flagImage: ImageView
    private lateinit var textViewOptionOne: TextView
    private lateinit var textViewOptionTwo: TextView
    private lateinit var textViewOptionThree: TextView
    private lateinit var textViewOptionFour: TextView
    private lateinit var checkButton: MaterialButton
    private lateinit var questionsList: MutableList<Question>
    private var questionCounter = 0
    private var selectedAnswer = 0
    private lateinit var currentQuestion: Question
    private var answered = false
    private lateinit var name: String
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        flagImage = findViewById(R.id.image_flag)
        textViewOptionOne = findViewById(R.id.text_view_option_one)
        textViewOptionTwo = findViewById(R.id.text_view_option_two)
        textViewOptionThree = findViewById(R.id.text_view_option_three)
        textViewOptionFour = findViewById(R.id.text_view_option_four)
        checkButton = findViewById(R.id.button_check)

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
        checkButton.setOnClickListener(this)


        questionsList = Constants.getQuestions()

        showNextQuestion()

        if (intent.hasExtra(Constants.USER_NAME)) {
            name = intent.extras?.getString(Constants.USER_NAME).toString()
        }
    }

    private fun showNextQuestion() {

        if (questionCounter < questionsList.size) {
            checkButton.text = getString(R.string.check).uppercase()
            currentQuestion = questionsList[questionCounter]
            resetOptions()
            val question = questionsList[questionCounter]
            flagImage.setImageResource(question.image)
            val progressBarValue = questionCounter + 1
            progressBar.progress = progressBarValue
            textViewProgress.text =
                getString(R.string.progress_text, progressBarValue, progressBar.max)
            textViewQuestion.text = question.question
            textViewOptionOne.text = question.optionOne
            textViewOptionTwo.text = question.optionTwo
            textViewOptionThree.text = question.optionThree
            textViewOptionFour.text = question.optionFour
        } else {
            checkButton.text = getString(R.string.concluir).uppercase()
            checkButton.setOnClickListener {
                Intent(this, ResultActivity::class.java)
                    .also {
                        it.putExtra(Constants.USER_NAME, name)
                        it.putExtra(Constants.SCORE, score)
                        it.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                        startActivity(it)
                        finish()
                    }
            }
        }


        questionCounter++
        answered = false
    }

    private fun resetOptions() {
        val options = mutableListOf<TextView>()
        options.add(textViewOptionOne)
        options.add(textViewOptionTwo)
        options.add(textViewOptionThree)
        options.add(textViewOptionFour)

        for (o in options) {
            o.setTextColor(Color.parseColor("#7A8089"))
            o.typeface = Typeface.DEFAULT
            o.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()
        selectedAnswer = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewOptionOne, 1)
            }

            R.id.text_view_option_two -> {
                selectedOption(textViewOptionTwo, 2)
            }

            R.id.text_view_option_three -> {
                selectedOption(textViewOptionThree, 3)
            }

            R.id.text_view_option_four -> {
                selectedOption(textViewOptionFour, 4)
            }

            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()
                } else {
                    resetOptions()
                    showNextQuestion()
                }
                selectedAnswer = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            highlight(selectedAnswer)
        } else {
            when (selectedAnswer) {
                1 -> textViewOptionOne.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

                2 -> textViewOptionTwo.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

                3 -> textViewOptionThree.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

                4 -> textViewOptionFour.background =
                    ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            }
        }

        checkButton.text = getString(R.string.next).uppercase()
        showSolution()
    }

    private fun showSolution() {
        selectedAnswer = currentQuestion.correctAnswer

        highlight(selectedAnswer)
    }

    private fun highlight(answer: Int) {
        when (answer) {
            1 -> textViewOptionOne.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)

            2 -> textViewOptionTwo.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)

            3 -> textViewOptionThree.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)

            4 -> textViewOptionFour.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
        }
    }
}