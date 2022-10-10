package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()
    var currCardDisplayedIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        val first_button = findViewById<TextView>(R.id.button1)
        val second_button = findViewById<TextView>(R.id.button2)
        val third_button = findViewById<TextView>(R.id.button3)
        val add_question_button = findViewById<ImageView>(R.id.add_question_button)

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
                    if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                        flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                    }
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
        val nextButton = findViewById<ImageView>(R.id.nextbutton)
        nextButton.setOnClickListener {
            first_button.visibility = View.INVISIBLE
            second_button.visibility = View.INVISIBLE
            third_button.visibility = View.INVISIBLE
            if (allFlashcards.isEmpty()) {
                // early return so that the rest of the code doesn't execute
                return@setOnClickListener
            }

            currCardDisplayedIndex++

            if (currCardDisplayedIndex >= allFlashcards.size) {
                // go back to the beginning
                currCardDisplayedIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currCardDisplayedIndex].question
            val answer = allFlashcards[currCardDisplayedIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer
        }
        findViewById<View>(R.id.trash).setOnClickListener {
            val flashcardQuestionToDelete = findViewById<TextView>(R.id.flashcard_question).text.toString()
            flashcardDatabase.deleteCard(flashcardQuestionToDelete)
        }
    }
}