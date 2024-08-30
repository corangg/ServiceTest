package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()
        var index = 0

        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        var currentLayout: LinearLayout = rootLayout

        try {
            for(i in 0.. 110000){//max243
                val newLayout = LinearLayout(this).apply {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )
                }
                index = i

                currentLayout.addView(newLayout)
                currentLayout = newLayout

                if (i % 10 == 0) {
                    logMemoryUsage("After $i layouts created")
                }
            }
        } catch (e: Exception){
            Log.d("LinearLayoutDrawTime", "LinearLayout draw Fail: $index")
        }

        val endTime = System.nanoTime()

        val duration = endTime - startTime
        Log.d("LinearLayoutDrawTime", "Max LinearLayout create time: $duration ns")
        currentLayout.addView(TextView(this).apply {
            text = "Max LinearLayout create time: $duration ns"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })

        rootLayout.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                rootLayout.viewTreeObserver.removeOnPreDrawListener(this)
                val totalDuration = System.nanoTime() - startTime
                Log.d(
                    "LinearLayoutDrawTime",
                    "Max LinearLayout draw time: $totalDuration ns"
                )

                // 화면에 표시되는 시간 텍스트뷰 추가
                currentLayout.addView(TextView(this@MaxActivity).apply {
                    text = "Max LinearLayout draw time: $totalDuration ns"
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(0, 60, 0, 0)
                    }
                })
                return true
            }
        })

        setContentView(rootLayout)
    }

    private fun logMemoryUsage(tag: String) {
        val runtime = Runtime.getRuntime()
        val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024
        val freeMemory = runtime.freeMemory() / 1024 / 1024
        val totalMemory = runtime.totalMemory() / 1024 / 1024
        val maxMemory = runtime.maxMemory() / 1024 / 1024

        Log.d(
            "MemoryMonitoring",
            "$tag: Used Memory = $usedMemory MB, Free Memory = $freeMemory MB, Total Memory = $totalMemory MB, Max Memory = $maxMemory MB"
        )
    }
}