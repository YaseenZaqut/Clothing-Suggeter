package com.example.clothingsuggester.data

import com.example.clothingsuggester.R

class ImageData {
    fun winterClothesImage(): Int {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.winter1)
        imageList.add(R.drawable.winter2)
        imageList.add(R.drawable.winter3)
        imageList.add(R.drawable.winter4)
        imageList.add(R.drawable.winter5)
        return imageList.random()
    }

    fun summerClothesImage(): Int {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.summer1)
        imageList.add(R.drawable.summer2)
        imageList.add(R.drawable.summer3)
        imageList.add(R.drawable.summer4)
        imageList.add(R.drawable.summer5)
        return imageList.random()
    }

    fun moderateClothesImage(): Int {
        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.spring1)
        imageList.add(R.drawable.spring2)
        imageList.add(R.drawable.spring3)
        imageList.add(R.drawable.spring4)
        imageList.add(R.drawable.spring5)
        return imageList.random()
    }
}