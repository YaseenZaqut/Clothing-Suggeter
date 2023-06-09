package com.example.clothingsuggester.data

import android.util.Log
import com.example.clothingsuggester.model.WeatherResponse
import com.google.gson.Gson
import okhttp3.*
import okio.IOException

class WeatherServiceImplementation  : WeatherService {
    lateinit var weatherResponse: WeatherResponse
    override fun makeRequestUsingOKHTTP(
        onGetCurrentWeatherSuccess: (weatherResponse: WeatherResponse) -> Unit,
        onGetCurrentWeatherFail: (e: okio.IOException) -> Unit
    ) {
        val client = OkHttpClient()
        val request = Request.Builder().url(getUrl()).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG , "Fail ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    weatherResponse =
                        Gson().fromJson(response.body?.string(), WeatherResponse::class.java)
                    onGetCurrentWeatherSuccess(weatherResponse)
                }
            }
        })
    }
    private fun getUrl(): HttpUrl {
        return HttpUrl
            .Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(PATH)
            .addQueryParameter(LAT, GAZA_LAT)
            .addQueryParameter(LON, GAZA_LON)
            .addQueryParameter(UNITS, "metric")
            .addQueryParameter(APP_KEY, "5591e9a22ae1fe0c591a03b968c6e3ff")
            .build()
    }
    companion object {
        private const val TAG = "main activity"
        private const val LAT = "lat"
        private const val LON = "lon"
        private const val UNITS = "units"
        private const val APP_KEY = "appid"
        private const val HOST = "api.openweathermap.org"
        private const val SCHEME = "https"
        private const val PATH = "/data/2.5/weather"
        private const val GAZA_LAT = "31.5019"
        private const val GAZA_LON = "34.4666"
    }


}