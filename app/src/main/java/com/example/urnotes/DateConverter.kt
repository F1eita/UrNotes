package com.example.urnotes

import android.content.Context
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateConverter {
    fun getMonth(numberOfMonth: String, context: Context): String{
        val res = context.resources
        when (numberOfMonth){
            "1" -> return res.getString(R.string.Jan)
            "2" -> return res.getString(R.string.Feb)
            "3" -> return res.getString(R.string.March)
            "4" -> return res.getString(R.string.April)
            "5" -> return res.getString(R.string.May)
            "6" -> return res.getString(R.string.June)
            "7" -> return res.getString(R.string.July)
            "8" -> return res.getString(R.string.Aug)
            "9" -> return res.getString(R.string.Sep)
            "10" -> return res.getString(R.string.Oct)
            "11" -> return res.getString(R.string.Nov)
            "12" -> return res.getString(R.string.Dec)
            else -> return "??"
        }
    }
    fun ConvertDate(date: String, context: Context): String{
        val res = context.resources
        if (LocalDate.now().format(DateTimeFormatter.ofPattern("M-d")).toString().equals(date)){
            return res.getString(R.string.today)
        }

        var numberOfMonth = ""
        var dayOfMonth = ""
        var isPoint = false
        for (i in date){
            when (isPoint){
                true -> dayOfMonth += i
                false -> {
                    if ((i != '.') and (i != '-')){
                        numberOfMonth += i
                    }
                    else isPoint = true
                }
            }
        }
        return "$dayOfMonth ${getMonth(numberOfMonth, context)}"
    }

}