package com.kr.ecomap.kiosk.fragmenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.kr.ecomap.kiosk.fragmenttest.databinding.FragmentOneBinding
import com.kr.ecomap.kiosk.fragmenttest.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startTime = System.nanoTime()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false)

        binding.toOneFragment.setOnClickListener {
            val action = ThreeFragmentDirections.actionThreeFragmentToOneFragment(0)
            findNavController().navigate(action)
        }

        binding.toTwoFragment.setOnClickListener {
            val action = ThreeFragmentDirections.actionThreeFragmentToTwoFragment(0)
            findNavController().navigate(action)
        }

        return binding.root.also {
            val runtime = Runtime.getRuntime()
            val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
            val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
            Log.d("FragmentMove", "Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

            val elapsedTime = System.nanoTime() - startTime
            Log.d("FragmentMove", "Fragment creation time: ${elapsedTime / 1000000} ms")
        }
    }
}