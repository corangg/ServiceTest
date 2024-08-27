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
          for(i in 0.. 1000){
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

        return rootLayout
    }
}