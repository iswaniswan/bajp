package com.iswan.main.movflix.data.source.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.data.models.Genre
import com.iswan.main.movflix.data.models.Season

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun genresToString(value: List<Genre>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToGenres(data: String?): List<Genre?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<List<Genre?>?>() {}.type
        return gson.fromJson<List<Genre?>>(data, listType)
    }

    @TypeConverter
    fun companiesToString(value: List<Company>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToCompanies(data: String?): List<Company?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<List<Company?>?>() {}.type
        return gson.fromJson<List<Company?>>(data, listType)
    }

    @TypeConverter
    fun seasonsToString(value: List<Season>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToSeasons(data: String?): List<Season?>? {
        if (data == null) return arrayListOf()
        val listType = object : TypeToken<List<Season?>?>() {}.type
        return gson.fromJson<List<Season?>>(data, listType)
    }
}