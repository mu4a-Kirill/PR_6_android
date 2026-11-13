package com.example.pr_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pr_6.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME  = "extra_name"
        const val EXTRA_GROUP = "extra_group"
        const val EXTRA_AGE   = "extra_age"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name  = intent.getStringExtra(EXTRA_NAME) ?: ""
        val group = intent.getStringExtra(EXTRA_GROUP) ?: ""
        val age   = intent.getIntExtra(EXTRA_AGE, 0)

        binding.tvData.text = getString(R.string.second_format, name, group, age)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}