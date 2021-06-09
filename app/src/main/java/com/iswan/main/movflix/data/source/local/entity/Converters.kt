package com.iswan.main.movflix.data.source.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.data.models.Genre
import com.iswan.main.movflix.data.models.Season

class Converters {
    val gson = Gson()

    @TypeConverter
    fun genresToString(value: ArrayList<Genre>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToGenres(data: String?): ArrayList<Genre?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<ArrayList<Genre?>?>() {}.type
        return gson.fromJson<ArrayList<Genre?>>(data, listType)
    }

    @TypeConverter
    fun companiesToString(value: ArrayList<Company>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToCompanies(data: String?): ArrayList<Company?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<ArrayList<Company?>?>() {}.type
        return gson.fromJson<ArrayList<Company?>>(data, listType)
    }

    @TypeConverter
    fun seasonsToString(value: ArrayList<Season>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToSeasons(data: String?): ArrayList<Season?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<ArrayList<Season?>?>() {}.type
        return gson.fromJson<ArrayList<Season?>>(data, listType)
    }
}