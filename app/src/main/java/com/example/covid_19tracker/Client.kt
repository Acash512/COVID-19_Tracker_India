package com.example.covid_19tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Client {
    val okHttpClient = OkHttpClient()

    val request = Request.Builder()
            .url("https://api.covid19india.org/data.json")
            .build()

    val api = okHttpClient.newCall(request)
}