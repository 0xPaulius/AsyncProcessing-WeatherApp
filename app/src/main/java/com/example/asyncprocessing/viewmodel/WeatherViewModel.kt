package com.example.asyncprocessing.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.asyncprocessing.data.DataLoader
import com.example.asyncprocessing.data.WeatherData

sealed class WeatherUIState {
    object Loading : WeatherUIState()
    data class Success(val data: List<WeatherData>) : WeatherUIState()
    data class Error(val message: String) : WeatherUIState()
}

class WeatherViewModel : ViewModel() {
    // pavogiau
    private val _weatherState = MutableStateFlow<WeatherUIState>(WeatherUIState.Loading)
    val weatherState = _weatherState.asStateFlow()

    private val _filterQuery = MutableStateFlow("")
    val filterQuery = _filterQuery.asStateFlow()

    init {
        println("WeatherViewModel: init called") // XDDD
        loadWeatherData()
    }

    private fun loadWeatherData() {
        println("WeatherViewModel: loadWeatherData called")
        DataLoader { weatherData ->
            println("WeatherViewModel: Data received from DataLoader") // filler
            _weatherState.value = WeatherUIState.Success(weatherData)
        }.execute()
    }

    fun updateFilter(query: String) {
        println("WeatherViewModel: updateFilter called with query: $query") // filler
        _filterQuery.value = query
    }
}