package com.example.urnotes

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateConverter {
    fun getMonth(numberOfMonth: String): String{
        when (numberOfMonth){
            "1" -> return "January"//R.string.Jan.toString()
            "2" -> return "February" //R.string.Feb.toString()
            "3" -> return "March" //R.string.March.toString()
            "4" -> return "April" //R.string.April.toString()
            "5" -> return "May" //R.string.May.toString()
            "6" -> return "June" //R.string.June.toString()
            "7" -> return "July" //R.string.July.toString()
            "8" -> return "August" //R.string.Aug.toString()
            "9" -> return "September" //R.string.Sep.toString()
            "10" -> return "October" //R.string.Oct.toString()
            "11" -> return "November"//R.string.Nov.toString()
            "12" -> return "December" //R.string.Dec.toString()
            else -> return "??"
        }
    }
    fun ConvertDate(date: String): String{

        if (LocalDate.now().format(DateTimeFormatter.ofPattern("M-d")).toString().equals(date)){
            return "Today"//R.string.today.toString()
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
        return "$dayOfMonth ${getMonth(numberOfMonth)}"
    }

}