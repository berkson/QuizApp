package com.berkson.quizapp.ui

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
    private val currentPosition = 1
    private lateinit var questionsList: MutableList<Question>
    private var selectedOptionPosition = 0

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

        setQuestion()
    }

    private fun setQuestion() {
        val question = questionsList[currentPosition - 1]
        flagImage.setImageResource(question.image)
        progressBar.progress = currentPosition
        textViewProgress.text = getString(R.string.progress_text, currentPosition, progressBar.max)
        textViewQuestion.text = question.question
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour

        if (currentPosition == questionsList.size) {
            checkButton.text = getString(R.string.concluir).uppercase()
        } else {
            checkButton.text = getString(R.string.check).uppercase()
        }
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
        selectedOptionPosition = selectedOptionNumber

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
                //TODO: checar de está correto
            }
        }
    }
}