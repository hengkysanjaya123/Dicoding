package com.example.itss_wsc.myapplication

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class getData(val callback: (result: String) -> Unit) :
    AsyncTask<String, Integer, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String {
        var result = ""

        try {
            val url = URL(params[0].toString())
            val con = url.openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(con.inputStream))

            var line = reader.readLine()
            while (line != null) {
                result += line
                line = reader.readLine()
            }

            con.disconnect()
        } catch (e: Exception) {
            Log.d("testing", e.toString())
        }
        return result
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        callback(result)
    }
}