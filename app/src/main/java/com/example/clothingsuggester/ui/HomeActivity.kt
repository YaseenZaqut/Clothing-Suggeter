package com.example.clothingsuggester.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.clothingsuggester.R
import com.example.clothingsuggester.data.domain.WeatherData
import com.example.clothingsuggester.databinding.ActivityHomeBinding
import com.example.clothingsuggester.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var temp : String
    val  client = OkHttpClient()
    val homeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSubView()

    }

    private fun initSubView() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container , homeFragment).commit()
    }



}