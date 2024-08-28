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

class HundredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()

        val rootLayout = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        var currentLayout: FrameLayout = rootLayout

        for (i in 1..100) {
            val newLayout = FrameLayout(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }

            currentLayout.addView(newLayout)
            currentLayout = newLayout
        }

        val endTime = System.nanoTime()

        val duration = endTime - startTime
        Log.d("FrameLayoutDrawTime", "Hundred FrameLayout create time: $duration ns")
        currentLayout.addView(TextView(this).apply {
            text = "Hundred FrameLayout create time: $duration ns"
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
                    "Hundred FrameLayout draw time: $totalDuration ns"
                )

                // 화면에 표시되는 시간 텍스트뷰 추가
                currentLayout.addView(TextView(this@HundredActivity).apply {
                    text = "Hundred FrameLayout draw time: $totalDuration ns"
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
}