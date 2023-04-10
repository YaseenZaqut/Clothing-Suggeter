package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clothingsuggester.R
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

    override fun onweatherDataSuccess(weatherResponse: WeatherResponse) {
        val temp = weatherResponse.main.temp
        runOnUiThread {
            binding.textviewTemp.text = temp.toString()
            if (temp > 25) {
                binding.imageClothes.setImageResource(getListOfImgSummerClothes()[0])
                Toast.makeText(this, "temp : $temp summer", Toast.LENGTH_SHORT).show()
            }else if (temp in 15.0..25.0){
                binding.imageClothes.setImageResource(getListOfImgSpringClothes()[0])
                Toast.makeText(this, "temp : $temp Spring", Toast.LENGTH_SHORT).show()
            } else {
                binding.imageClothes.setImageResource(getListOfImgWinterClothes()[0])
                Toast.makeText(this, "temp : $temp winter", Toast.LENGTH_SHORT).show()
            }
        }


    }


}