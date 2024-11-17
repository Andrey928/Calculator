package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput = ""
    private var operator = ""
    private var operand1 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.textView1)
    }

    fun b1Click(view: View) {
        when ((view as Button).text) {
            "C" -> clear()
            "=" -> calculateResult()
            else -> handleInput(view.text.toString())
        }
    }

    private fun clear() {
        currentInput = ""
        operator = ""
        operand1 = 0.0
        resultTextView.text = "00"
    }

    private fun handleInput(input: String) {
        if (input in listOf("+", "-", "*", "/")) {
            if (currentInput.isNotEmpty()) {
                operand1 = currentInput.toDouble()
                operator = input
                currentInput = ""
            }
            return
        }

        currentInput += input
        resultTextView.text = currentInput
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val operand2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> if (operand2 != 0.0) operand1 / operand2 else Double.NaN
                else -> 0.0
            }


            if (result.isNaN()) {
                resultTextView.text = "Ошибка: Деление на 0"
            } else {
                resultTextView.text = result.toString()
                operand1 = result 
            }

            currentInput = ""
            operator = ""
        }
    }
}