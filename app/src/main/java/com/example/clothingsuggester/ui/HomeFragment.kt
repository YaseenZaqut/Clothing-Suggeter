package com.example.clothingsuggester.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.clothingsuggester.R
import com.example.clothingsuggester.data.domain.WeatherData
import com.example.clothingsuggester.databinding.FragmentHomeBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class HomeFragment : Fragment() {
     var temp: Double = 0.0
    val client = OkHttpClient()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

            makeRequestUsingOKHTTP()
        return binding.root
    }


    private fun makeRequestUsingOKHTTP() {
        Log.i(TAG, "make Request")
        val request = Request.Builder().url(getUrl()).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG, "${e.message}")
            }
            override fun onResponse(call: Call, response: Response) {
                response.body?.string().let { jsonString ->
                    temp = Gson().fromJson(jsonString, WeatherData::class.java).main.temp
                }
                runOnUiThread()
            }
        })
    }

    private fun getUrl(): HttpUrl {
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
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.jacket11)
        imageList.add(R.drawable.jacket12)
        imageList.add(R.drawable.jacket14)
        imageList.add(R.drawable.jacket15)
        return imageList.shuffled()
    }

    private fun getListOfImgSummerClothes(): List<Int> {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.summer1)
        imageList.add(R.drawable.summer2)
        imageList.add(R.drawable.summer3)
        imageList.add(R.drawable.summer4)
        imageList.add(R.drawable.summer5)
        return imageList.shuffled()
    }


    private fun runOnUiThread() {
        binding.textviewTemp.text = temp.toString()
        activity?.runOnUiThread {
            if (temp > 25) {
                binding.imageClothes.setImageResource(getListOfImgSummerClothes()[0])
                Toast.makeText(activity, "tepm : $temp summer", Toast.LENGTH_SHORT).show()
            } else {
                binding.imageClothes.setImageResource(getListOfImgWinterClothes()[0])
                Toast.makeText(activity, "tepm : $temp winter", Toast.LENGTH_SHORT).show()


            }
        }
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