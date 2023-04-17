package com.example.clothingsuggester.data

import java.text.SimpleDateFormat
import java.util.*

class DateData {
    private val calendar= Calendar.getInstance()
    private val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val currentDate = Date()
    private val date = dateFormat.format(currentDate)

    fun date (): String = date
    fun day (): String = dayOfWeek
}