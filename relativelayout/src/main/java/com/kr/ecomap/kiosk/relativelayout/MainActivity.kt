package com.kr.ecomap.kiosk.relativelayout

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.kr.ecomap.kiosk.relativelayout.databinding.ActivityMainBinding

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
    }
}