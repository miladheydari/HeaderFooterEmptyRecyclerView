package com.miladheydari.headerfooteremptyrecyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.miladheydari.hferecyclerview.HFEAdapter

class Adapter(_data: List<String>?) : HFEAdapter<String>(_data) {
    override fun getItemView(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {

        return ViewHolder(inflater.inflate(R.layout.row, parent,false))

    }

    override fun onBindViewHolder(holder: android.support.v7.widget.RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.tv.text = getItem(position)

            }
            else -> {
            }

        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv: TextView = view.findViewById(R.id.tv)

    }
}