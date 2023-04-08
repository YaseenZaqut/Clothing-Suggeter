package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.clothingsuggester.R
import com.example.clothingsuggester.data.domain.WeatherData
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var temp : String
    val  client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        makeRequestUsingOKHTTP()

    }

    private fun makeRequestUsingOKHTTP() {
        Log.i(TAG , "make Request")
        val request = Request.Builder().url(getUrl()).build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG , "error request ")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {jsonString ->
                    temp = Gson().fromJson(jsonString , WeatherData::class.java).main.temp.toString()
                }
              runOnUiThread()
            }

        })



    }


    private fun getUrl(): HttpUrl{
        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(PATH)
            .addQueryParameter(LAT, GAZA_LAT)
            .addQueryParameter(LON, GAZA_LON)
            .addQueryParameter(UNITS, "metric")
            .addQueryParameter(APP_KEY, "5591e9a22ae1fe0c591a03b968c6e3ff")
            .build()
        return url
    }

    private fun getListOfImgWinterClothes(): List<Int> {
        val bitmapList = mutableListOf<Int>()
        bitmapList.add(R.drawable.jacket)
        bitmapList.add(R.drawable.jacket2)
        return bitmapList.shuffled()
    }

    private fun getListOfImgSummerClothes(): List<Int> {
        val bitmapList = mutableListOf<Int>()
        bitmapList.add(R.drawable.tshirt)
        bitmapList.add(R.drawable.tshirt1)
        return bitmapList.shuffled()
    }


    private fun runOnUiThread(){
        runOnUiThread {
            if (temp.toDouble()>10) {
                binding.imageClothes.setImageResource(getListOfImgSummerClothes()[0])
                Toast.makeText(this@HomeActivity , "$temp  summer" , Toast.LENGTH_SHORT).show()
            }
            else{
                binding.imageClothes.setImageResource(getListOfImgWinterClothes()[0])
                Toast.makeText(this@HomeActivity, "$temp  winter" , Toast.LENGTH_SHORT).show()

            }
        }
    }

    companion object{
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