package com.bamboogeeks.julyacademy2.resturantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOrder.setOnClickListener {
            val checkedRadioButtonId = binding.rgMeals.checkedRadioButtonId
            val burger = findViewById<RadioButton>(checkedRadioButtonId)
            val cheese = binding.chkCheese.isChecked
            val salad = binding.chkSalad.isChecked
            val onionRings = binding.chkOnionRings.isChecked
            val totalOrder = "You just ordered a burger with \n" + "${burger.text}"+
                    (if(cheese)"\n Cheese" else "")+
                    (if(salad)"\n Salad" else "")+
                    (if(onionRings)"\n Onion Rings" else "")

            binding.tvTotalOrder.text = totalOrder

        }
    }
}