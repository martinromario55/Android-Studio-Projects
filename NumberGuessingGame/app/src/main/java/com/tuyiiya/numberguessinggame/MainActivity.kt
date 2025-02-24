package com.tuyiiya.numberguessinggame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.tuyiiya.numberguessinggame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraintLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.buttonStart.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            if (!mainBinding.radioButtonOne.isChecked && !mainBinding.radioButtonTwo.isChecked && !mainBinding.radioButtonThree.isChecked) {
                Snackbar.make(mainBinding.constraintLayout, "Please select a number of digits", Snackbar.LENGTH_LONG).show()
            } else {
                if (mainBinding.radioButtonOne.isChecked) {
                    intent.putExtra("number", "one")
                } else if (mainBinding.radioButtonTwo.isChecked) {
                    intent.putExtra("number", "two")
                } else {
                    intent.putExtra("number", "three")
                }

                startActivity(intent)
            }
        }
    } // End of onCreate Fun
}