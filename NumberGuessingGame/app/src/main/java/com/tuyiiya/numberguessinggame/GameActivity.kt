package com.tuyiiya.numberguessinggame

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tuyiiya.numberguessinggame.databinding.ActivityGameBinding
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    lateinit var gameBinding: ActivityGameBinding
    var randomNumber: Int = -1
    var remainingRight = 8

    var steps = 0
    var guesses = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        val view = gameBinding.root

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gameBinding.toolbarGame.setNavigationOnClickListener {
            finish()
        }

        gameBinding.textViewHint.visibility = View.INVISIBLE
        gameBinding.textViewLastGuess.visibility = View.INVISIBLE
        gameBinding.textViewRight.visibility = View.INVISIBLE

        when (intent.getStringExtra("number").toString()) {
            "one" -> {
                gameBinding.textViewInfo.text = "Guess a number between 0 - 9"
                randomNumber = Random.nextInt(0, 10)
                remainingRight = 3
            }
            "two" -> {
                gameBinding.textViewInfo.text = "Guess a number between 10 - 99"
                randomNumber = Random.nextInt(10, 100)
                remainingRight = 8
            }
            "three" -> {
                gameBinding.textViewInfo.text = "Guess a number between 100 - 999"
                randomNumber = Random.nextInt(100, 1000)
                remainingRight = 13
            }
            else -> Toast.makeText(applicationContext, "There is a problem", Toast.LENGTH_SHORT).show()
        }

        gameBinding.buttonConfirm.setOnClickListener {
            gameLogic(randomNumber)
        }
    } // End of onCreate fun

    fun gameLogic(randomNumber: Int) {
        val guess: String = gameBinding.editTextNumber.text.toString()

        if (guess == "") { // Check if null
            Toast.makeText(applicationContext, "Please enter a guess", Toast.LENGTH_LONG).show()
        } else {
            try { // Check if Integer value
                val userGuess = guess.toInt()

                if (randomNumber == -1) { // Check if positive integer
                    Toast.makeText(applicationContext, "Random number cannot be -1, please try again", Toast.LENGTH_LONG).show()
                } else {
                    steps++ // Increase the steps
                    guesses.add(userGuess) // Add user's guess to the list

                    if (randomNumber == userGuess) { // Correct Guess
                        createDialogMessage()
                    } else {
                        remainingRight-- // Reduce remaining attempts

                        if (remainingRight == 0) { // Check remaining attempts
                            createDialogMessage()
                        } else { // Provide guess hint
                            if (userGuess < randomNumber) {
                                gameBinding.textViewHint.text = "Increase your guess"
                            } else {
                                gameBinding.textViewHint.text = "Decrease your guess"
                            }
                        }
                    }

                    gameBinding.textViewRight.text = "Your remaining right is $remainingRight"
                    gameBinding.textViewLastGuess.text = "Your last guess is $userGuess"

                    gameBinding.textViewHint.visibility = View.VISIBLE
                    gameBinding.textViewLastGuess.visibility = View.VISIBLE
                    gameBinding.textViewRight.visibility = View.VISIBLE

                    gameBinding.editTextNumber.setText("")
                }

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Please enter a valid guess", Toast.LENGTH_LONG).show()
            }
        }
    } // End of gameLogic fun

    fun createDialogMessage() {
        val dialogBox = AlertDialog.Builder(this@GameActivity)
        dialogBox.setTitle("Number Guessing Game")

        if (remainingRight > 0) { // User won
            dialogBox.setMessage("\t Congratulations, The number in my mind was $randomNumber." +
                    "\n\n\t You know my number in $steps steps." +
                    "\n\n\t Your guesses: $guesses" +
                    "\n\n\t Do you want to play again?")
        } else { // User lost
            dialogBox.setMessage("\t Sorry, your right to guess is over." +
                    "\n\n\t The number in my mind was $randomNumber" +
                    "\n\n\t Your guesses: $guesses" +
                    "\n\n\t Do you want to play again?")
        }
        dialogBox.setCancelable(false)
        dialogBox.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, which ->
            finishAffinity() // Close Game
        })

        dialogBox.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, which ->
            val intent = Intent(this@GameActivity, MainActivity::class.java)
            startActivity(intent) // Restart game
            finish()
        })

        dialogBox.create().show()
    } // End of createDialogMessage fun
}