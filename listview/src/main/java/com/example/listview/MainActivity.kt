package com.example.listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val items = listOf("0","1","2","3","4")
        val adapter = ListAdapter(this,R.layout.item_list, items)

        binding.gridView.adapter = adapter

        //binding.listView.adapter = adapter
    }
}