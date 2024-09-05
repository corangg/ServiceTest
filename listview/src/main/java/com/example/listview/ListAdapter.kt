package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ListAdapter(context: Context, private val resource: Int, private val items: List<String>)
    : ArrayAdapter<String>(context, resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = convertView ?: layoutInflater.inflate(resource, parent, false)

        val item = items[position]
        val textView = view.findViewById<TextView>(R.id.item_text)
        textView.text = item

        return view
    }
}