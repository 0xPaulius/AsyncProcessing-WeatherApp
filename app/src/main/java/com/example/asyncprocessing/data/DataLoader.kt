package com.example.asyncprocessing.data

import android.os.AsyncTask
import java.net.URL

@Suppress("DEPRECATION")  // WTF?
class DataLoader(private val callback: (List<WeatherData>) -> Unit) : AsyncTask<Void, Void, List<WeatherData>>() {
    companion object {
        private const val API_URL = "https://api.meteo.lt/v1/places/kaunas/forecasts/long-term"
    }

    @Deprecated("Deprecated in Java")  // WTF?
    override fun doInBackground(vararg params: Void): List<WeatherData> {
        println("DataLoader: doInBackground called")
        return try {
            val response = URL(API_URL).readText()
            Parser.parse(response)
        } catch (e: Exception) {
            println("DataLoader error: ${e.message}")
            emptyList()
        }
    }

    @Deprecated("Deprecated in Java")  // WTF?
    override fun onPostExecute(result: List<WeatherData>) {
        println("DataLoader: onPostExecute called")
        callback(result)
    }
}