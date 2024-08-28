package com.example.framelayout

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.FrameLayout
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

        val rootLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        var currentLayout: FrameLayout = rootLayout

        try {
            for (i in 1..244) {//max 244
                val newLayout = FrameLayout(this).apply {
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                }
                index = i

                currentLayout.addView(newLayout)
                currentLayout = newLayout

                if (i % 10 == 0) {
                    logMemoryUsage("After $i layouts created")
                }
            }
        }catch (e: Exception){
            Log.d("FrameLayoutDrawTime", "FrameLayout draw Fail: $index")
        }

        val endTime = System.nanoTime()

        val duration = endTime - startTime
        Log.d("FrameLayoutDrawTime", "Hundred FrameLayout create time: $duration ns")
        currentLayout.addView(TextView(this).apply {
            text = "Max FrameLayout create time: $duration ns"
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        })

        rootLayout.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                rootLayout.viewTreeObserver.removeOnPreDrawListener(this)
                val totalDuration = System.nanoTime() - startTime
                Log.d(
                    "FrameLayoutDrawTime",
                    "Max FrameLayout draw time: $totalDuration ns"
                )

                // 화면에 표시되는 시간 텍스트뷰 추가
                currentLayout.addView(TextView(this@MaxActivity).apply {
                    text = "Max FrameLayout draw time: $totalDuration ns"
                    layoutParams = FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT
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