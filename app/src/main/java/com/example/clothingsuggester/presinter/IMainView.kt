package com.example.clothingsuggester.presinter

import com.example.clothingsuggester.model.WeatherResponse
import okio.IOException

interface IMainView {
    fun onWeatherDataSuccess(weatherResponse: WeatherResponse)
    fun onWeatherDataFail(e: IOException)
}