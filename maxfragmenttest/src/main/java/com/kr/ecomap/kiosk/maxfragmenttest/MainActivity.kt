package com.kr.ecomap.kiosk.maxfragmenttest

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var fragmentCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragments()
    }

    private fun addFragments() {
        val fragmentManager = supportFragmentManager

        try {
            while (true) {
                fragmentCount++

                val fragment = TestFragment.newInstance(fragmentCount)

                fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, "fragment$fragmentCount")
                    .addToBackStack("fragment$fragmentCount")
                    .commit()

                logMemoryUsage()

                runOnUiThread {
                    findViewById<TextView>(R.id.fragmentCountTextView).text = "Fragment Count: $fragmentCount"
                }

                Thread.sleep(500)
            }
        } catch (e: Exception) {
            Log.e("MaxFragmentLog", "Error adding fragment: ${e.message}")
        }
    }

    private fun logMemoryUsage() {
        val runtime = Runtime.getRuntime()
        val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
        val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
        Log.d("MaxFragmentLog", "Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")
    }
}
