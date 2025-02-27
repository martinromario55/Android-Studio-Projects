package com.tuyiiya.balloonpoppinggame

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tuyiiya.balloonpoppinggame.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var resultBinding: ActivityResultBinding

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        resultBinding = ActivityResultBinding.inflate(layoutInflater)
        val view = resultBinding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val myScore = intent.getIntExtra("score", 0)
        resultBinding.textViewMyScore.text = "Your Score: $myScore"

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE)
        val highestScore = sharedPreferences.getInt("highestScore", 0)

        if (myScore > highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore).apply()
            resultBinding.textViewHighestScore.text = "Highest Score: $myScore"
            resultBinding.textViewInfo.text = "Congratulations! You set a new High Score!"
        } else {
            resultBinding.textViewHighestScore.text = "Highest Score: $highestScore"

            if ((highestScore - myScore) > 10) {
                resultBinding.textViewInfo.text = "Too Slow. Speed up!"
            } else if ((highestScore - myScore) in 4..10) {
                resultBinding.textViewInfo.text = "How about getting a little faster?"
            } else {
                resultBinding.textViewInfo.text = "Great! You're almost reaching your high score."
            }
        }

        resultBinding.buttonPlayAgain.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        resultBinding.buttonQuit.setOnClickListener {
            finishAffinity()
        }
    } // End of onCreate
}