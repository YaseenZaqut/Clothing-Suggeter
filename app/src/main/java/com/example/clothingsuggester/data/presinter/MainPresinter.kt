package com.example.clothingsuggester.data.presinter

import com.example.clothingsuggester.data.domain.WeatherService
import com.example.clothingsuggester.data.model.WeatherResponse
import com.example.clothingsuggester.ui.IMainView
import javax.security.auth.callback.Callback

class MainPresinter(private val view : IMainView)  {
    private val weatherService= WeatherService()
    fun presentWeatherState() {
        weatherService.makeRequestUsingOKHTTP (::onGetWeatherState)
    }
    private fun onGetWeatherState(weatherResponse: WeatherResponse){
        view.onweatherDataSuccess(weatherResponse)

    }
}