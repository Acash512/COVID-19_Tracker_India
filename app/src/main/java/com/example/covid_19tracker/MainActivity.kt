package com.example.covid_19tracker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getInfo()
    }

    private fun getInfo() {
        Client.api.clone().enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                this@MainActivity.runOnUiThread {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful) {
                    response.body?.let {
                        val myResponse = it.string()

                        this@MainActivity.runOnUiThread {
                            val json = JSONObject(myResponse)
                            val stateCodesIterator = json.keys()
                            val listStateInfo = ArrayList<StateInfo>()

                            while (stateCodesIterator.hasNext()) {
                                val stateCode = stateCodesIterator.next()
                                val stateInfo = Gson().fromJson(
                                    json.getJSONObject(stateCode).toString(),
                                    StateInfo::class.java
                                )

                                if (stateCode == "TT") {
                                    listStateInfo.add(0, stateInfo)
                                } else {
                                    listStateInfo.add(stateInfo)
                                }

                                if (mapStateCodes.containsKey(stateCode))
                                    stateInfo.nameOfState = mapStateCodes[stateCode]
                            }

                            bindCombinedData(listStateInfo[0])
                            bindStatewiseData(listStateInfo)
                        }
                    }
                }
            }

        })
    }

    private fun bindStatewiseData(listStateInfo: ArrayList<StateInfo>) {
        val adapter = StateWiseAdapter(listStateInfo)
        lvStatewise.adapter = adapter
    }

    private fun bindCombinedData(stateInfo: StateInfo) {
        val lastUpdated = stateInfo.meta?.last_updated
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US)

        lastUpdated?.let {
            sdf.parse(it)?.let { date->
                lastUpdatedTv.text = getString(R.string.last_updated_time,getTimeAgo(date))
            }
        }

        tvConfirmed.text = stateInfo.total?.confirmed ?: "0"
        tvVaccinated.text = stateInfo.total?.vaccinated2 ?: "0"
        tvRecovered.text = stateInfo.total?.recovered ?: "0"
        tvDeceased.text = stateInfo.total?.deceased ?: "0"
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
                SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US).format(past)
            }
        }
    }
}