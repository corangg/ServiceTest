package com.example.linearlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.linearlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        binding.btnOneLayout.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            startActivity(intent)
        }

        binding.btnHundredLayout.setOnClickListener {
            val intent = Intent(this, HundredActivity::class.java)
            startActivity(intent)
        }

        binding.btnMaxLayout.setOnClickListener {
            val intent = Intent(this, MaxActivity::class.java)
            startActivity(intent)
        }
    }
}