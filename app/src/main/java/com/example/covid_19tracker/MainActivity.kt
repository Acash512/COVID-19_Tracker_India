package com.example.covid_19tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInfo()
    }

    private fun getInfo() {
        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO){
                Client.api.clone().execute()
            }

            if(response.isSuccessful) {
                val info = Gson().fromJson(response.body?.string(), Info::class.java)
                bindCombinedData(info.statewise[0])
                bindStatewiseData(info.statewise)
            }
        }
    }

    private fun bindStatewiseData(statewise: List<StatewiseItem>) {
        val adapter = StateWiseAdapter(statewise)
        lvStatewise.adapter = adapter
    }

    private fun bindCombinedData(statewiseItem: StatewiseItem) {
        val lastUpdated = statewiseItem.lastupdatedtime!!
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        lastUpdatedTv.text = "Last Updated ${getTimeAgo(sdf.parse(lastUpdated)!!)}"
        tvConfirmed.text = statewiseItem.confirmed
        tvActive.text = statewiseItem.active
        tvRecovered.text = statewiseItem.recovered
        tvDeceased.text = statewiseItem.deaths
    }

    private fun getTimeAgo(past: Date): String {
        val now = Date()
        val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time-past.time)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time-past.time)
        val hours = TimeUnit.MILLISECONDS.toHours(now.time-past.time)

        return when{
            seconds<60 -> {
                "Few Seconds ago"
            }

            minutes<60 -> {
                "$minutes minutes ago"
            }

            hours<24 -> {
                "$hours hours ${minutes%60} minutes ago"
            }

            else -> {
                SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(past)
            }
        }
    }
}