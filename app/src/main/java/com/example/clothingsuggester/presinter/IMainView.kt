package com.example.clothingsuggester.presinter

import com.example.clothingsuggester.model.WeatherResponse

interface IMainView {
    fun onWeatherDataSuccess(weatherResponse: WeatherResponse)
}