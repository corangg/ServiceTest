package com.kr.ecomap.kiosk.fragmenttest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kr.ecomap.kiosk.fragmenttest.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding: FragmentTwoBinding
    private val args: TwoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val startTime = System.nanoTime()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false)

        val index = args.data

        fragmentTest(startTime)

        binding.toOneFragment.setOnClickListener {
            val action = TwoFragmentDirections.actionTwoFragmentToOneFragment(0,0)
            findNavController().navigate(action)
        }

        binding.toThreeFragment.setOnClickListener {
            val action = TwoFragmentDirections.actionTwoFragmentToThreeFragment()
            findNavController().navigate(action)
        }

        return binding.root.also {
            val runtime = Runtime.getRuntime()
            val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
            val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
            Log.d("FragmentMove", "$index Fragment Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

            val elapsedTime = System.nanoTime() - startTime
            Log.d("FragmentMove", "Fragment creation time: ${elapsedTime / 1000000} ms")
        }
    }

    private fun fragmentTest(startTime: Long){
        val index = args.data
        val argsStartTime = args.time
        if(index == 0){
            val action = TwoFragmentDirections.actionTwoFragmentToOneFragment(index + 1,startTime)
            findNavController().navigate(action)
        }else if(index == 5000){

            val runtime = Runtime.getRuntime()
            val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
            val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
            Log.d("FragmentMove", "Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

            val elapsedTime = System.nanoTime() - argsStartTime
            Log.d("FragmentMove", "Fragment creation time: ${elapsedTime / 1000000} ms")
        }else{
            val action = TwoFragmentDirections.actionTwoFragmentToOneFragment(index + 1,argsStartTime)
            findNavController().navigate(action)
        }
    }
}