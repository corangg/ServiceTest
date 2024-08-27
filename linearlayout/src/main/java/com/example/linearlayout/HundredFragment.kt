package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class HundredFragment : Fragment() {
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

        var currentLayout: LinearLayout = rootLayout

        for (i in 1..100) {
            val newLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                )
            }

            currentLayout.addView(newLayout)
            currentLayout = newLayout
        }
        val newLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        }

        currentLayout.addView(newLayout)
        currentLayout = newLayout

        val endTime = System.nanoTime()

        val duration = endTime - startTime
        Log.d("LinearLayoutDrawTime", "Hundred LinearLayout and draw time: $duration ns")
        currentLayout.addView(TextView(requireContext()).apply {
            text = "Hundred LinearLayout and draw time: $duration ns"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })

        return rootLayout
    }
}