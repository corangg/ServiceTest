package com.example.constraintlayout

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HundredActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.nanoTime()

        val rootLayout = ConstraintLayout(this).apply {
            id = View.generateViewId()
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
        }

        var currentLayout: ConstraintLayout = rootLayout
        for (i in 1..100) {
            val newLayout = ConstraintLayout(this).apply {
                id = View.generateViewId()
                layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT
                )
            }
            currentLayout.addView(newLayout)
            currentLayout = newLayout
        }

        val endTime = System.nanoTime()
        val duration = endTime - startTime
        Log.d("ConstraintLayoutDrawTime", "Hundred ConstraintLayout create time: $duration ns")
        val createTimeTextView = TextView(this).apply {
            id = View.generateViewId()
            text = "Hundred ConstraintLayout create time: $duration ns"
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        currentLayout.addView(createTimeTextView)

        rootLayout.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                rootLayout.viewTreeObserver.removeOnPreDrawListener(this)
                val totalDuration = System.nanoTime() - startTime
                Log.d(
                    "ConstraintLayoutDrawTime",
                    "Hundred ConstraintLayout draw time: $totalDuration ns"
                )

                // 화면에 표시되는 시간 텍스트뷰 추가
                val drawTimeTextView = TextView(this@HundredActivity).apply {
                    id = View.generateViewId()
                    text = "Hundred ConstraintLayout draw time: $totalDuration ns"
                    layoutParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                currentLayout.addView(drawTimeTextView)
                val constraintSet = ConstraintSet()
                constraintSet.clone(currentLayout)

                constraintSet.connect(createTimeTextView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
                constraintSet.connect(drawTimeTextView.id, ConstraintSet.TOP, createTimeTextView.id, ConstraintSet.BOTTOM, 16)

                constraintSet.applyTo(currentLayout)

                return true
            }
        })

        setContentView(rootLayout)
    }
}