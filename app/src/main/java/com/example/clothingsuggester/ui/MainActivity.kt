package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.clothingsuggester.data.DateData
import com.example.clothingsuggester.data.ImageData
import com.example.clothingsuggester.model.WeatherResponse
import com.example.clothingsuggester.presinter.IMainView
import com.example.clothingsuggester.presinter.MainPresenter
import com.example.clothingsuggester.databinding.ActivityMainBinding
import okio.IOException

class MainActivity : AppCompatActivity(), IMainView {
    val TAG = "main activity"
    lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)
    private val imageData = ImageData()
    private val dateData = DateData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }
    private fun setup() {
        presenter.presentWeatherState()
    }
    override fun onWeatherDataSuccess(weatherResponse: WeatherResponse) {
        val temp = weatherResponse.main.temp
        val description = weatherResponse.weather[0].description

        runOnUiThread {
            Toast.makeText(this, "temp : $temp", Toast.LENGTH_SHORT).show()
            binding.let {
                it.textviewDescription.text = description
                it.textviewTemp.text = temp.toInt().toString()
                it.textviewToday.text = dateData.day()
                it.textviewDate.text = dateData.date()
            }
            if (temp > 25) {
                binding.imageClothes.setImageResource(imageData.summerClothesImage(this))
            } else if (temp in 15.0..25.0) {
                binding.imageClothes.setImageResource(imageData.moderateClothesImage(this))
            } else {
                binding.imageClothes.setImageResource(imageData.winterClothesImage(this))
            }
        }
    }
    override fun onWeatherDataFail(e: IOException) {
        Log.i(TAG, "fail ${e.message}")
    }
}