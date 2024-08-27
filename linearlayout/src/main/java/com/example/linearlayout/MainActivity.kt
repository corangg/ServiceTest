package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_linear_one, OneFragment())
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_linear_hundred, HundredFragment())
                .commit()

            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_linear_ten_thousand,TenThousandFragment())
                .commit()
        }
    }
}