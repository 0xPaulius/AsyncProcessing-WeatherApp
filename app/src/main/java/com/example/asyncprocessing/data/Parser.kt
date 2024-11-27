package com.example.asyncprocessing.data

import org.json.JSONObject

object Parser {
    fun parse(jsonString: String): List<WeatherData> {
        println("Parser: parse method called")
        return try {
            println("Parser: Starting JSON parsing")
            val json = JSONObject(jsonString)
            val forecastTimestamps = json.getJSONArray("forecastTimestamps")
            val weatherList = mutableListOf<WeatherData>()

            for (i in 0 until forecastTimestamps.length()) { // XDDD nzn bet kazkodel veikia
                val item = forecastTimestamps.getJSONObject(i)
                weatherList.add(
                    WeatherData(
                        forecastTimeUtc = item.getString("forecastTimeUtc"),
                        airTemperature = item.getDouble("airTemperature"),
                        feelsLikeTemperature = item.getDouble("feelsLikeTemperature"),
                        windSpeed = item.getInt("windSpeed"),
                        windGust = item.getInt("windGust"),
                        windDirection = item.getInt("windDirection"),
                        cloudCover = item.getInt("cloudCover"),
                        relativeHumidity = item.getInt("relativeHumidity"),
                        totalPrecipitation = item.getDouble("totalPrecipitation"),
                        conditionCode = item.getString("conditionCode")
                    )
                )
            }
            println("Parser: Successfully parsed ${weatherList.size} items") // as nenusisausiu
            weatherList
        } catch (e: Exception) {
            println("Parser Error: ${e.message}")  // as nusisausiu
            emptyList()
        }
    }
}