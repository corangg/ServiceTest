package com.kr.ecomap.kiosk.relativelayout

import android.os.Bundle
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
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

        val rootLayout = RelativeLayout(this).apply {
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        }

        var currentLayout: RelativeLayout = rootLayout
        try {
            for (i in 1..100000) {
                val newLayout = RelativeLayout(this).apply {
                    layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                    )
                }
                index = i

                currentLayout.addView(newLayout)
                currentLayout = newLayout

                if (i % 10 == 0) {
                    logMemoryUsage("After $i layouts created")
                }
            }
        }catch (e:Exception){
            Log.d("RelativeLayoutDrawTime", "RelativeLayout draw Fail: $index")
        }

        val duration = System.nanoTime() - startTime
        //뷰 생성 시간 측정
        Log.d("RelativeLayoutDrawTime", "Max RelativeLayout create time: $duration ns")
        currentLayout.addView(TextView(this).apply {
            text = "Max RelativeLayout create time: $duration ns"
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        })

        rootLayout.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                rootLayout.viewTreeObserver.removeOnPreDrawListener(this)
                val totalDuration = System.nanoTime() - startTime
                Log.d(
                    "RelativeLayoutDrawTime",
                    "Max RelativeLayout draw time: $totalDuration ns"
                )

                // 화면에 표시되는 시간 텍스트뷰 추가
                currentLayout.addView(TextView(this@MaxActivity).apply {
                    text = "Max RelativeLayout draw time: $totalDuration ns"
                    layoutParams = RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
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