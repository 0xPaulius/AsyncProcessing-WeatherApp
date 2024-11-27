package com.example.asyncprocessing.data

data class WeatherData(
    val forecastTimeUtc: String,
    val airTemperature: Double,
    val feelsLikeTemperature: Double,
    val windSpeed: Int,
    val windGust: Int,
    val windDirection: Int,
    val cloudCover: Int,
    val relativeHumidity: Int,
    val totalPrecipitation: Double, //ka?
    val conditionCode: String
)