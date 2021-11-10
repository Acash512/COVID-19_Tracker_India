package com.example.covid_19tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class StateWiseAdapter(private val list:List<StateInfo>):BaseAdapter() {

    override fun getCount(): Int = list.size

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView = convertView?:LayoutInflater.from(parent?.context).inflate(R.layout.list_item,parent,false)
        itemView.apply{
            tvState.text = list[position].nameOfState

            tvConfirmedState.text = SpannableData(
                "${list[position].total?.confirmed ?: 0}\n ↑${list[position].delta7?.confirmed ?: 0}",
                 "#D32F2F",
                list[position].total?.confirmed?.length?:1
            )

            tvVaccinatedState.text = SpannableData(
                "${list[position].total?.vaccinated2 ?: 0}\n ↑${list[position].delta7?.vaccinated2 ?: 0}",
                "#1976D2",
                list[position].total?.vaccinated2?.length ?: 1
            )

            tvRecoveredState.text = SpannableData(
                "${list[position].total?.recovered ?: 0}\n ↑${list[position].delta7?.recovered ?: 0}",
                "#388E3C",
                list[position].total?.recovered?.length ?: 1
            )

            tvDeceasedState.text = SpannableData(
                "${list[position].total?.deceased ?: 0}\n ↑${list[position].delta7?.deceased ?: 0}",
                "#FBC02D",
                list[position].total?.deceased?.length ?: 1
            )
        }
        return itemView
    }
}