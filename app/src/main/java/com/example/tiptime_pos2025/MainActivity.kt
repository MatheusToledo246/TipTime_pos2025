package com.example.tiptime_pos2025

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tiptime_pos2025.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater)

        setContentView( binding.root)

        binding.calculateButton.setOnClickListener{
            calculateTip()
        }

    }

    private fun calculateTip() {
        val strintInTextField = binding.costOfService.text.toString()
        val cost = strintInTextField.toDoubleOrNull() ?: return

        val selectedId = binding.tipOption.checkedRadioButtonId

        val tipPercentage = when (selectedId){
            R.id.option_twenty_percent -> 0.2
            R.id.oprion_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format( tip )

        binding.tipResult.text = getString( R.string.tip_amount_s, tip.toString())
    }
}