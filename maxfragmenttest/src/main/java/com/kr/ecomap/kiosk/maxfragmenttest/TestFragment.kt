package com.kr.ecomap.kiosk.maxfragmenttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class TestFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }

    companion object {
        fun newInstance(fragmentNumber: Int): TestFragment {
            val fragment = TestFragment()
            val args = Bundle()
            args.putInt("fragmentNumber", fragmentNumber)
            fragment.arguments = args
            return fragment
        }
    }
}