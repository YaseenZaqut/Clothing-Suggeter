package com.example.clothingsuggester.presinter

import com.example.clothingsuggester.data.WeatherServiceImplementation
import com.example.clothingsuggester.model.WeatherResponse
import okio.IOException

class MainPresenter(private val view : IMainView)  {
    private val weatherService= WeatherServiceImplementation()
    fun presentWeatherState() {
        weatherService.makeRequestUsingOKHTTP (::onGetCurrentWeatherSuccess , ::onGetCurrentWeatherFail)
    }
    private fun onGetCurrentWeatherSuccess(weatherResponse: WeatherResponse){
        view.onWeatherDataSuccess(weatherResponse)
    }private fun onGetCurrentWeatherFail(e: IOException){
        view.onWeatherDataFail(e)
    }
}