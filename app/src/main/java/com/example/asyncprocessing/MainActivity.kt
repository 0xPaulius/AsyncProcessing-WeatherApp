package com.example.asyncprocessing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import com.example.asyncprocessing.ui.weatherScreen
import com.example.asyncprocessing.viewmodel.WeatherViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // super.kaiveikia
        setContent {
            MaterialTheme {
                weatherScreen(viewModel)
            }
        }
    }
}