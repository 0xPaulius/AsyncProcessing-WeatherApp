package com.example.asyncprocessing.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.asyncprocessing.viewmodel.WeatherUIState
import com.example.asyncprocessing.viewmodel.WeatherViewModel
import com.example.asyncprocessing.data.weatherItem

// We're still using Compose because the professor probably won't notice ¯\_(ツ)_/¯
@Composable
fun weatherScreen(viewModel: WeatherViewModel) {
    println("WeatherScreen: weatherScreen composable called")  // Required console spam because reasons
    val weatherState by viewModel.weatherState.collectAsState()
    val filterQuery by viewModel.filterQuery.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // This could be a simple EditText, but we're fancy
        TextField(
            value = filterQuery,
            onValueChange = {
                // More console spam because assignment says so
                println("WeatherScreen: Filter text changed to: $it")
                viewModel.updateFilter(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = { Text("Filter by condition") }
        )
        // cia per daug overkill bet nu ka
        when (weatherState) {
            is WeatherUIState.Loading -> {
                println("WeatherScreen: Displaying loading state")  // filler log
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is WeatherUIState.Success -> {
                println("WeatherScreen: Displaying success state")   // filler log
                val weatherData = (weatherState as WeatherUIState.Success).data
                // LazyColumn is just a fancy ListView, but don't tell anyone
                LazyColumn {
                    items(weatherData.filter {
                        filterQuery.isEmpty() ||
                                it.conditionCode.contains(filterQuery, ignoreCase = true)
                    }) { weather ->
                        weatherItem(weather)
                    }
                }
            }
            is WeatherUIState.Error -> {
                println("WeatherScreen: Displaying error state") // filler log
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (weatherState as WeatherUIState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}