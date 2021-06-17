package com.iswan.main.movflix.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.iswan.main.movflix.data.source.remote.rest.Config
import java.text.DecimalFormat
import java.text.NumberFormat

object Utils {

    fun getImagePath(size: Int, path: String): String {
        var url = Config.TMDB_URL + Config.TMDB_PATH
        url += when (size) {
            0 -> Config.TMDB_ICON
            1 -> Config.TMDB_SMALL
            else -> Config.TMDB_MEDIUM
        }
        url += path
        return url
    }

    fun showNotifSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }

    fun currencyString(number: Int): String{
        if (number == 0) return "-"
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(number)
        return "$ $formattedNumber"
    }

    fun currencyString(number: Long): String{
        if (number.toInt() == 0) return "-"
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(number)
        return "$ $formattedNumber"
    }

    fun yearStr(yyyymmdd: String?): String {
        if (yyyymmdd != null && yyyymmdd.toString().isNotEmpty()) {
            val timeArray = yyyymmdd.split("-".toRegex()).dropLastWhile { it.isEmpty() }
            return timeArray.get(0).toString()
        } else {
            return "-"
        }
    }

}