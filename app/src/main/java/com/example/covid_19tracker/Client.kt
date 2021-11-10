package com.example.covid_19tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    private val okHttpClient = OkHttpClient()

    private val request = Request.Builder()
            .url("https://data.covid19india.org/v4/min/data.min.json")
            .build()

    val api = okHttpClient.newCall(request)
}