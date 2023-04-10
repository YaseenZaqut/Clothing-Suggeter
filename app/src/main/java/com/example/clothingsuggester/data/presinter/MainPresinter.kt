package com.example.clothingsuggester.data.presinter

import com.example.clothingsuggester.data.domain.WeatherService
import com.example.clothingsuggester.data.model.Main
import com.example.clothingsuggester.data.model.WeatherData
import com.example.clothingsuggester.ui.IMainView

class MainPresinter {
    private val api = WeatherService()
    lateinit var view: IMainView
    fun getUserInfo() {

        val result = api.makeRequestUsingOKHTTP()
        view.onweatherDataSuccess(result)
    }
}