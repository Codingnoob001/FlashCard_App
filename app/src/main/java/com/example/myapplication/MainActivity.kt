package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val first_button = findViewById<Button>(R.id.button1)
        val second_button = findViewById<Button>(R.id.button2)
        val third_button = findViewById<Button>(R.id.button3)
        val xbutton = findViewById<ImageView>(R.id.X_button)
        val add_question_button = findViewById<ImageView>(R.id.add_question_button)
        val editbutton = findViewById<ImageView>(R.id.editbutton)
        val savebutton = findViewById<ImageView>(R.id.savebutton)
        flashcardQuestion.setOnClickListener {
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }
        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        }
        first_button.setOnClickListener {
            first_button.setBackgroundColor(getResources().getColor(R.color.red))
            second_button.setBackgroundColor(getResources().getColor(R.color.green))
            third_button.setBackgroundColor(getResources().getColor(R.color.red))
            flashcardAnswer.visibility = View.VISIBLE
        }
        second_button.setOnClickListener {
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
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val data: Intent? = result.data
                if (data != null) {
                    val questionString = data.getStringExtra("QUESTION_KEY")
                    val answerString = data.getStringExtra("ANSWER_KEY")
                    flashcardQuestion.text = questionString
                    flashcardAnswer.text = answerString
                    Log.i("victor: MainActivity", "question: $questionString")
                    Log.i("victor: MainActivity", "answer: $answerString")
                } else {
                    Log.i("victor: MainActivity", "Returned null data from AddCardActivity")
                }
            }
        add_question_button.setOnClickListener {
            val intent = Intent(this, AddcardActivity::class.java)
            resultLauncher.launch(intent)
            first_button.visibility = View.INVISIBLE
            second_button.visibility = View.INVISIBLE
            third_button.visibility = View.INVISIBLE
        }

    }
}