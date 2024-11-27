package com.example.asyncprocessing.data

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// This could be way simpler, but we're trying to make it look professional
@Composable
fun weatherItem(weather: WeatherData) {
    println("WeatherScreen: WeatherItem composable called")  // privalomas log spam
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // We're showing raw UTC time because parsing dates is too mainstream
            Text(
                text = weather.forecastTimeUtc,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // sita maciau githube kazkur bet nezinau kur
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Temperature")
                    Text(text = "${weather.airTemperature}°C")
                }
                Column {
                    Text(text = "Wind")
                    Text(text = "${weather.windSpeed} m/s")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "Condition")
                    Text(text = weather.conditionCode)
                }
                Column {
                    Text(text = "Humidity")
                    Text(text = "${weather.relativeHumidity}%")
                }
            }
        }
    }
}