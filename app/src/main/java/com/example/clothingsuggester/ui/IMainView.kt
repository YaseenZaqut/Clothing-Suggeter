package com.example.clothingsuggester.ui

import com.example.clothingsuggester.data.model.WeatherData

interface IMainView {
    fun onweatherDataSuccess(weatherData: WeatherData)
}