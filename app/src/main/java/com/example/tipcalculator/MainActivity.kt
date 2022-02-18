package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding   // special class for accessing layout items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // activate the binding
        setContentView(binding.root)    // set the binding.root to current activity

        binding.btnCalc.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val costString = binding.editCost.text.toString()  // user input is string
        val cost = costString.toDouble()                   // convert it to double (numeric)
        val selectedId = binding.radioTipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.radioTwenty -> 0.20
            R.id.radioEighteen -> 0.18
            R.id.radioOkay -> 0.15
            else -> 0.0
        }
        var tip = tipPercentage * cost
        val roundUp = binding.switchRoundUp.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.textTipTotal.text = getString(R.string.tip_amt, formattedTip)
    }


}