package com.kr.ecomap.kiosk.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.kr.ecomap.kiosk.activitytest.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding : ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)
        binding.lifecycleOwner = this

        binding.toTwoActivity.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        binding.toOneActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val runtime = Runtime.getRuntime()
        val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
        val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
        Log.d("ActivityMove", "Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

        val elapsedTime = System.nanoTime() - startTime
        Log.d("ActivityMove", "Activity creation time: ${elapsedTime / 1000000} ms")

    }
}