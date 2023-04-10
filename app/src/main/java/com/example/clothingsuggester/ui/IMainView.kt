package com.example.clothingsuggester.ui

import com.example.clothingsuggester.data.model.WeatherResponse

interface IMainView {
    fun onweatherDataSuccess(weatherResponse: WeatherResponse)
}