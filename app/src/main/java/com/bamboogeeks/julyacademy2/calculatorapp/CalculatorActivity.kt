package com.bamboogeeks.julyacademy2.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bamboogeeks.julyacademy2.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddition.setOnClickListener {
            if (binding.etFirstNumber.text.isNotEmpty() && binding.etSecondNumber.text.isNotEmpty()) {
                val firstNumber = binding.etFirstNumber.text.toString().toInt()
                val secondNumber = binding.etSecondNumber.text.toString().toInt()
                val result = firstNumber + secondNumber
                binding.tvResult.text = result.toString()
            } else {
                Toast.makeText(this, "Not Valid Input", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnSub.setOnClickListener {
            if (binding.etFirstNumber.text.isNotEmpty() && binding.etSecondNumber.text.isNotEmpty()) {
                val firstNumber = binding.etFirstNumber.text.toString().toInt()
                val secondNumber = binding.etSecondNumber.text.toString().toInt()
                val result = firstNumber - secondNumber
                binding.tvResult.text = result.toString()
            } else {
                Toast.makeText(this, "Not Valid Input", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnMultiply.setOnClickListener {
            if (binding.etFirstNumber.text.isNotEmpty() && binding.etSecondNumber.text.isNotEmpty()) {
                val firstNumber = binding.etFirstNumber.text.toString().toInt()
                val secondNumber = binding.etSecondNumber.text.toString().toInt()
                val result = firstNumber * secondNumber
                binding.tvResult.text = result.toString()
            } else {
                Toast.makeText(this, "Not Valid Input", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnDivide.setOnClickListener {
            if (binding.etFirstNumber.text.isNotEmpty() && binding.etSecondNumber.text.isNotEmpty()) {
                val firstNumber = binding.etFirstNumber.text.toString().toInt()
                val secondNumber = binding.etSecondNumber.text.toString().toInt()
                if (secondNumber == 0) {
                    Toast.makeText(this, "Cannot Divide by Zero", Toast.LENGTH_LONG).show()
                } else {
                    val result = firstNumber / secondNumber
                    binding.tvResult.text = result.toString()
                }

            } else {
                Toast.makeText(this, "Not Valid Input", Toast.LENGTH_LONG).show()
            }
        }
        binding.btnResetValues.setOnClickListener {
            binding.etFirstNumber.text.clear()
            binding.etSecondNumber.text.clear()
            binding.tvResult.setText("Result Here ::")
            Toast.makeText(this, "Value Cleared", Toast.LENGTH_LONG).show()

        }
    }
}