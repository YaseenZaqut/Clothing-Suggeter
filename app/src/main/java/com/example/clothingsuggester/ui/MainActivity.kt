package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clothingsuggester.data.model.WeatherResponse
import com.example.clothingsuggester.data.presinter.MainPresinter
import com.example.clothingsuggester.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , IMainView {
    lateinit var binding: ActivityMainBinding
    private val presinter = MainPresinter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        presinter.presentWeatherState()
    }


    override fun onweatherDataSuccess(weatherResponse: WeatherResponse) {
        val temp =  weatherResponse.main.temp
        runOnUiThread {
            binding.textviewTemp.text = temp.toString()

            if (temp in 15.0..25.0){

            }
        }
    }


}