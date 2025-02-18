package com.berkson.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.berkson.quizapp.MainActivity
import com.berkson.quizapp.R
import com.berkson.quizapp.utils.Constants
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.MainScope

class ResultActivity : AppCompatActivity() {
    private var score = 0
    private var questions = 0
    private lateinit var textScore: TextView
    private lateinit var textName: TextView
    private lateinit var finish: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        supportActionBar?.hide()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textName = findViewById(R.id.textview_name)
        textScore = findViewById(R.id.textview_score)
        finish = findViewById(R.id.finish_button)
        score = intent.getIntExtra(Constants.SCORE, 0)
        questions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        textName.text = intent.getStringExtra(Constants.USER_NAME).toString()
        textScore.text = getString(R.string.sua_pontua_o_foi_pontos, score, questions)

        finish.setOnClickListener{
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}