package com.example.clothingsuggester.presinter

import com.example.clothingsuggester.data.WeatherService
import com.example.clothingsuggester.model.WeatherResponse

class MainPresenter(private val view : IMainView)  {
    private val weatherService= WeatherService()
    fun presentWeatherState() {
        weatherService.makeRequestUsingOKHTTP (::onGetWeatherState)
    }
    private fun onGetWeatherState(weatherResponse: WeatherResponse){
        view.onWeatherDataSuccess(weatherResponse)

    }
}