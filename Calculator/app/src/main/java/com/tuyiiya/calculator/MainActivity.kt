package com.tuyiiya.calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tuyiiya.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var number: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        mainBinding.textViewResult.text = "0"

        mainBinding.btnZero.setOnClickListener {
            onNumberClicked("0")
        }
        mainBinding.btnOne.setOnClickListener {
            onNumberClicked("1")
        }
        mainBinding.btnTwo.setOnClickListener {
            onNumberClicked("2")
        }
        mainBinding.btnThree.setOnClickListener {
            onNumberClicked("3")
        }
        mainBinding.btnFour.setOnClickListener {
            onNumberClicked("4")
        }
        mainBinding.btnFive.setOnClickListener {
            onNumberClicked("5")
        }
        mainBinding.btnSix.setOnClickListener {
            onNumberClicked("6")
        }
        mainBinding.btnSeven.setOnClickListener {
            onNumberClicked("7")
        }
        mainBinding.btnEight.setOnClickListener {
            onNumberClicked("8")
        }
        mainBinding.btnNine.setOnClickListener {
            onNumberClicked("9")
        }
    }

    fun onNumberClicked(clickedNumber: String) {
        if (number == null) {
            number = clickedNumber
        } else {
            number += clickedNumber // Add clicked number to the end of the number string
        }

//        Display the number on the text view Result
        mainBinding.textViewResult.text = number
    }
}