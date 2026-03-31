package com.lihao.kotlinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainListAdapter(val context: Context, val items: List<MainListItem>): BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any? {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        if (convertView != null) {
            val textView = convertView.findViewById<TextView>(R.id.pageName)
            textView.text = items[position].pageName
            convertView.setOnClickListener { view -> items[position].action() }
            return convertView
        } else {
            val layoutInflater = LayoutInflater.from(context)
            val itemView = layoutInflater.inflate(R.layout.view_main_list_item, parent, false)
            val textView = itemView.findViewById<TextView>(R.id.pageName)
            textView.text = items[position].pageName
            itemView.setOnClickListener { view -> items[position].action() }
            return itemView
        }
    }
}