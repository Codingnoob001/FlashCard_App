package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class AddcardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcard)
        val user_question = findViewById<EditText>(R.id.editTextField)
        val user_answer = findViewById<EditText>(R.id.editTextField2)
        val xbutton = findViewById<ImageView>(R.id.X_button)
        val editbutton = findViewById<ImageView>(R.id.editbutton)
        xbutton.setOnClickListener{
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }
        val save_button = findViewById<ImageView>(R.id.savebutton)
        save_button.setOnClickListener {
            var questionString = user_question.text.toString()
            var answerString = user_answer.text.toString()
            var data = Intent()
            data.putExtra("QUESTION_KEY", questionString)
            data.putExtra("ANSWER_KEY", answerString)
            setResult(RESULT_OK, data)
            finish()
        }
        editbutton.setOnClickListener{
            val intent = Intent(this, AddcardActivity::class.java)
            startActivity(intent)
        }
    }
}