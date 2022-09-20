package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val first_button = findViewById<Button>(R.id.button1)
        val second_button = findViewById<Button>(R.id.button2)
        val third_button = findViewById<Button>(R.id.button3)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
         }
        flashcardAnswer.setOnClickListener{
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        }
        first_button.setOnClickListener{
            first_button.setBackgroundColor(getResources().getColor(R.color.red))
            second_button.setBackgroundColor(getResources().getColor(R.color.green))
            third_button.setBackgroundColor(getResources().getColor(R.color.red))
            flashcardAnswer.visibility = View.VISIBLE
        }
        second_button.setOnClickListener{
            first_button.setBackgroundColor(getResources().getColor(R.color.red))
            second_button.setBackgroundColor(getResources().getColor(R.color.green))
            third_button.setBackgroundColor(getResources().getColor(R.color.red))
            flashcardAnswer.visibility = View.VISIBLE
     }
        third_button.setOnClickListener {
            first_button.setBackgroundColor(getResources().getColor(R.color.red))
            second_button.setBackgroundColor(getResources().getColor(R.color.green))
            third_button.setBackgroundColor(getResources().getColor(R.color.red))
            flashcardAnswer.visibility = View.VISIBLE
        }
    }
}