package com.example.clothingsuggester.data

import android.content.Context
import com.example.clothingsuggester.R

class ImageData {
    fun winterClothesImage(context: Context): Int {
        val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.winter1)
        imageList.add(R.drawable.winter2)
        imageList.add(R.drawable.winter3)
        imageList.add(R.drawable.winter4)
        imageList.add(R.drawable.winter5)
        val img = imageList.random()

        return if (img != prefs.getInt("winter", img)) {
            prefs.edit().putInt("winter", img).apply()
            img
        } else {
            imageList.random()
        }
    }

    fun summerClothesImage(context: Context): Int {
        val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.summer1)
        imageList.add(R.drawable.summer2)
        imageList.add(R.drawable.summer3)
        imageList.add(R.drawable.summer4)
        imageList.add(R.drawable.summer5)
        val img = imageList.random()

        return if (img != prefs.getInt("summer", img)) {
            prefs.edit().putInt("summer", img).apply()
            img
        } else {
            imageList.random()
        }
    }

    fun moderateClothesImage(context: Context): Int {
        val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.spring1)
        imageList.add(R.drawable.spring2)
        imageList.add(R.drawable.spring3)
        imageList.add(R.drawable.spring4)
        imageList.add(R.drawable.spring5)
        val img = imageList.random()

        return if (img != prefs.getInt("moderate", img)) {
            prefs.edit().putInt("moderate", img).apply()
            img
        } else {
            imageList.random()
        }
    }
}