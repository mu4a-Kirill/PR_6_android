package com.example.pr_6

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pr_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            validateAndShowResult()
        }

        binding.btnNext.setOnClickListener {
            if (validateInput()) {
                openSecondScreen()
            }
        }

        binding.btnNext.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> view.alpha = 0.6f   // палец нажат
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> view.alpha = 1.0f  // отпустили
            }
            false
        }
    }


    private fun validateInput(): Boolean {
        val name = binding.etFullName.text.toString().trim()
        val group = binding.etGroup.text.toString().trim()
        val ageText = binding.etAge.text.toString().trim()

        if (name.split(Regex("\\s+")).size < 2) {
            Toast.makeText(this, R.string.error_name, Toast.LENGTH_SHORT).show()
            return false
        }
        if (group.isEmpty()) {
            Toast.makeText(this, R.string.error_group, Toast.LENGTH_SHORT).show()
            return false
        }
        val age = ageText.toIntOrNull()
        if (age == null || age !in 14..100) {
            Toast.makeText(this, R.string.error_age, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateAndShowResult() {
        if (!validateInput()) return

        val name = binding.etFullName.text.toString().trim()
        val group = binding.etGroup.text.toString().trim()
        val age = binding.etAge.text.toString().toInt()

        val base = getString(R.string.result_format, name, group, age)
        val status = getString(
            if (age >= 18) R.string.result_status_adult
            else R.string.result_status_minor
        )
        binding.tvResult.text = "$base\n$status"
    }

    private fun openSecondScreen() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(SecondActivity.EXTRA_NAME, binding.etFullName.text.toString().trim())
            putExtra(SecondActivity.EXTRA_GROUP, binding.etGroup.text.toString().trim())
            putExtra(SecondActivity.EXTRA_AGE, binding.etAge.text.toString().toInt())
        }
        startActivity(intent)
    }
}