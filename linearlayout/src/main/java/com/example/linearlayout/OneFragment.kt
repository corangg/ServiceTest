package com.example.linearlayout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView

class OneFragment : Fragment() {

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
        Log.d("LinearLayoutDrawTime", "One LinearLayout and draw time: $duration ns")
        currentLayout.addView(TextView(requireContext()).apply {
            text = "One LinearLayout and draw time: $duration ns"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })

        return rootLayout
    }
}