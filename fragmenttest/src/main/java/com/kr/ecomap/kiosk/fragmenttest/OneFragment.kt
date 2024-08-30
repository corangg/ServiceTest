package com.kr.ecomap.kiosk.fragmenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.kr.ecomap.kiosk.fragmenttest.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding: FragmentOneBinding
    private val args: OneFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val startTime = System.nanoTime()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)

        val index = args.data

        fragmentTest(startTime)

        binding.toTwoFragment.setOnClickListener {
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(0,0)
            findNavController().navigate(action)
        }

        binding.toThreeFragment.setOnClickListener {
            val action = OneFragmentDirections.actionOneFragmentToThreeFragment()
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
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(index + 1,startTime)
            findNavController().navigate(action)
        }else if(index == 5000){

            val runtime = Runtime.getRuntime()
            val usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L
            val maxHeapSizeInMB = runtime.maxMemory() / 1048576L
            Log.d("FragmentMove", "Total Memory Usage: $usedMemInMB MB / $maxHeapSizeInMB MB")

            val elapsedTime = System.nanoTime() - argsStartTime
            Log.d("FragmentMove", "Total Fragment creation time: ${elapsedTime / 1000000} ms")
        }else{
            val action = OneFragmentDirections.actionOneFragmentToTwoFragment(index + 1,argsStartTime)
            findNavController().navigate(action)
        }
    }
}