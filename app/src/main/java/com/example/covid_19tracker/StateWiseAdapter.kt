package com.example.covid_19tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class StateWiseAdapter(private val list:List<StatewiseItem>):BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView?:LayoutInflater.from(parent?.context).inflate(R.layout.list_item,parent,false)
        itemView.apply{
            tvState.text = list[position].state

            tvConfirmedState.text = SpannableData(
                "${list[position].confirmed}\n ↑${list[position].deltaconfirmed}",
                 "#D32F2F",
                list[position].confirmed?.length?:0
            )

            tvActiveState.text = SpannableData(
                "${list[position].active}\n ↑${list[position].deltaactive?:0}",
                "#1976D2",
                list[position].active?.length?:0
            )

            tvRecoveredState.text = SpannableData(
                "${list[position].recovered}\n ↑${list[position].deltarecovered}",
                "#388E3C",
                list[position].recovered?.length?:0
            )

            tvDeceasedState.text = SpannableData(
                "${list[position].deaths}\n ↑${list[position].deltadeaths}",
                "#FBC02D",
                list[position].deaths?.length?:0
            )
        }
        return itemView
    }
}