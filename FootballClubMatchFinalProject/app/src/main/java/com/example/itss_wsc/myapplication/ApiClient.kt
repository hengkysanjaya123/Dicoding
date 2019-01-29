package com.example.itss_wsc.myapplication

import java.net.URL

class ApiClient {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}