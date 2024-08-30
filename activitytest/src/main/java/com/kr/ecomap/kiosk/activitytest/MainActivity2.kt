package com.kr.ecomap.kiosk.activitytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.kr.ecomap.kiosk.activitytest.databinding.ActivityMain2Binding
import com.kr.ecomap.kiosk.activitytest.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.lifecycleOwner = this

        val index = intent.getIntExtra("index",0)
        activityTest(startTime)

        binding.toOneActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.toThreeActivity.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        val runtime = Runtime.getRuntime()
        val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
        val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
        Log.d("ActivityMove", "$index Activity Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

        val elapsedTime = System.nanoTime() - startTime
        Log.d("ActivityMove", "Activity creation time: ${elapsedTime / 1000000} ms")
    }
    private fun activityTest(startTime: Long){
        val intentStartTime = intent.getLongExtra("startTime",0)
        try {
            val index = intent.getIntExtra("index",0)
            if(index == 0){
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("index",index + 1)
                intent.putExtra("startTime", startTime)
                startActivity(intent)
            }else if(index == 1000){
                val runtime = Runtime.getRuntime()
                val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
                val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
                Log.d("ActivityMove", "Total Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

                val elapsedTime = System.nanoTime() - intentStartTime
                Log.d("ActivityMove", "Total Activity creation time: ${elapsedTime / 1000000} ms")
            }else{
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("index",index + 1)
                intent.putExtra("startTime", intentStartTime)
                startActivity(intent)
            }
        }catch (e: Exception){
            val elapsedTime = System.nanoTime() - intentStartTime
            Log.d("ActivityMove", "Total Activity creation time: ${elapsedTime / 1000000} ms")
            Log.e("ActivityMove", "Unexpected error: ${e.message}", e)
        }

    }
}