package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class TenThousandFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startTime = System.nanoTime()
        logMemoryUsage("Before creating layouts")

        val rootLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }
        var index = 0
        var currentLayout: LinearLayout = rootLayout
        try {
          for(i in 0.. 250){
              val newLayout = LinearLayout(requireContext()).apply {
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
            Log.d("LinearLayoutDrawTime", "LinearLayout and draw Fail: $index")
        }

        val endTime = System.nanoTime()

        val duration = endTime - startTime
        Log.d("LinearLayoutDrawTime", "$index LinearLayout and draw time: $duration ns")
        currentLayout.addView(TextView(requireContext()).apply {
            text = "$index LinearLayout and draw time: $duration ns"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })
        logMemoryUsage("After all layouts created")
        return rootLayout
    }

    private fun logMemoryUsage(tag: String) {
        val runtime = Runtime.getRuntime()
        val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024 // MB 단위
        val freeMemory = runtime.freeMemory() / 1024 / 1024 // MB 단위
        val totalMemory = runtime.totalMemory() / 1024 / 1024 // MB 단위
        val maxMemory = runtime.maxMemory() / 1024 / 1024 // MB 단위

        Log.d(
            "MemoryMonitoring",
            "$tag: Used Memory = $usedMemory MB, Free Memory = $freeMemory MB, Total Memory = $totalMemory MB, Max Memory = $maxMemory MB"
        )
    }
}