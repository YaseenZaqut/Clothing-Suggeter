package com.example.clothingsuggester.data

import com.example.clothingsuggester.model.WeatherResponse
import okio.IOException

interface WeatherService {
    fun makeRequestUsingOKHTTP(
        onGetCurrentWeatherSuccess : (weatherResponse : WeatherResponse) -> Unit,
        onGetCurrentWeatherFail : (e : IOException) -> Unit,
    )
}