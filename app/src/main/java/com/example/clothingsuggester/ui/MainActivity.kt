package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clothingsuggester.R
import com.example.clothingsuggester.model.WeatherResponse
import com.example.clothingsuggester.presinter.IMainView
import com.example.clothingsuggester.presinter.MainPresenter
import com.example.clothingsuggester.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), IMainView {
    lateinit var binding: ActivityMainBinding
    private val presinter = MainPresenter(this)
    val calendar = Calendar.getInstance()
    val dayOfWeek =
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = Date()
    val date = dateFormat.format(currentDate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        presinter.presentWeatherState()
    }

    fun getListOfImgWinterClothes(): List<Int> {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.winter1)
        imageList.add(R.drawable.winter2)
        imageList.add(R.drawable.winter3)
        imageList.add(R.drawable.winter4)
        imageList.add(R.drawable.winter5)
        return imageList.shuffled()
    }

    fun getListOfImgSummerClothes(): List<Int> {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.summer1)
        imageList.add(R.drawable.summer2)
        imageList.add(R.drawable.summer3)
        imageList.add(R.drawable.summer4)
        imageList.add(R.drawable.summer5)
        return imageList.shuffled()
    }

    fun getListOfImgSpringClothes(): List<Int> {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.spring1)
        imageList.add(R.drawable.spring2)
        imageList.add(R.drawable.spring3)
        imageList.add(R.drawable.spring4)
        imageList.add(R.drawable.spring5)
        return imageList.shuffled()
    }

    override fun onWeatherDataSuccess(weatherResponse: WeatherResponse) {
        val temp = weatherResponse.main.temp
        val description = weatherResponse.weather[0].description

        runOnUiThread {
            Toast.makeText(this, "temp : $temp", Toast.LENGTH_SHORT).show()
            binding.let {
                it.textviewDescription.text = description
                it.textviewTemp.text = temp.toInt().toString()
                it.textviewToday.text = dayOfWeek.toString()
                it.textviewDate.text = date.toString()
            }
            if (temp > 25) {
                binding.imageClothes.setImageResource(getListOfImgSummerClothes()[0])
            } else if (temp in 15.0..25.0) {
                binding.imageClothes.setImageResource(getListOfImgSpringClothes()[0])
            } else {
                binding.imageClothes.setImageResource(getListOfImgWinterClothes()[0])
            }
        }


    }


}