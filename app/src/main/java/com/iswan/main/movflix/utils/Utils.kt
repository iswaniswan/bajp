package com.iswan.main.movflix.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.iswan.main.movflix.data.Config
import com.iswan.main.movflix.data.models.*
import java.text.DecimalFormat
import java.text.NumberFormat

class Utils {

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

    fun arrToMovieList(jsonArray: JsonArray) : ArrayList<MovieEntity>{
        val list : ArrayList<MovieEntity> = arrayListOf()
        for (i in 0 until jsonArray.size()) {
            val movie = MovieEntity(
                jsonArray.get(i).asJsonObject.get("id").asInt,
                jsonArray.get(i).asJsonObject.get("poster_path").asString,
                jsonArray.get(i).asJsonObject.get("overview").asString,
                jsonArray.get(i).asJsonObject.get("release_date").asString,
                jsonArray.get(i).asJsonObject.get("title").asString,
                jsonArray.get(i).asJsonObject.get("vote_average").asDouble
            )
            list.add(movie)
        }
        return list
    }

    fun arrToTvList(jsonArray: JsonArray) : ArrayList<TvShowEntity>{
        val list : ArrayList<TvShowEntity> = arrayListOf()
        for (i in 0 until jsonArray.size()) {
            val tv = TvShowEntity(
                jsonArray.get(i).asJsonObject.get("id").asInt,
                jsonArray.get(i).asJsonObject.get("poster_path").asString,
                jsonArray.get(i).asJsonObject.get("overview").asString,
                jsonArray.get(i).asJsonObject.get("first_air_date").asString,
                jsonArray.get(i).asJsonObject.get("name").asString,
                jsonArray.get(i).asJsonObject.get("vote_average").asDouble
            )
            list.add(tv)
        }
        return list
    }

    fun showNotifSnackbar(view: View, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
    }
    
    fun jsonToMovieDetail(json: JsonObject): MovieDetailEntity {
        return MovieDetailEntity(
            json.asJsonObject.get("adult").asBoolean,
            json.asJsonObject.get("backdrop_path").asString,
            json.asJsonObject.get("budget").asInt,
            arrToGenres(
                json.asJsonObject.get("genres").asJsonArray
            ),
            json.asJsonObject.get("homepage").asString,
            json.asJsonObject.get("id").asInt,
            json.asJsonObject.get("original_language").asString,
            json.asJsonObject.get("original_title").asString,
            json.asJsonObject.get("overview").asString,
            json.asJsonObject.get("popularity").asDouble,
            json.asJsonObject.get("poster_path").asString,
            arrToCompanies(
                json.asJsonObject.get("production_companies").asJsonArray
            ),
            json.asJsonObject.get("release_date").asString,
            json.asJsonObject.get("revenue").asInt,
            json.asJsonObject.get("runtime").asInt,
            json.asJsonObject.get("status").asString,
            json.asJsonObject.get("tagline").asString,
            json.asJsonObject.get("title").asString,
            json.asJsonObject.get("vote_average").asDouble,
            json.asJsonObject.get("vote_count").asDouble
        )
    }

    fun jsonToTvShowDetail(json: JsonObject): TvShowDetailEntity {
        return TvShowDetailEntity(
            json.asJsonObject.get("backdrop_path").asString,
            json.asJsonObject.get("episode_run_time").asJsonArray.get(0).asInt,
            json.asJsonObject.get("first_air_date").asString,
            arrToGenres(
                json.asJsonObject.get("genres").asJsonArray
            ),
            json.asJsonObject.get("homepage").asString,
            json.asJsonObject.get("id").asInt,
            json.asJsonObject.get("last_air_date").asString,
            json.asJsonObject.get("name").asString,
            json.asJsonObject.get("number_of_seasons").asInt,
            json.asJsonObject.get("original_language").asString,
            json.asJsonObject.get("overview").asString,
            json.asJsonObject.get("popularity").asDouble,
            json.asJsonObject.get("poster_path").asString,
            arrToCompanies(
                json.asJsonObject.get("production_companies").asJsonArray
            ),
            arrToSeasons(
                json.asJsonObject.get("seasons").asJsonArray
            ),
            json.asJsonObject.get("status").asString,
            json.asJsonObject.get("tagline").asString,
            json.asJsonObject.get("vote_average").asDouble,
            json.asJsonObject.get("vote_count").asDouble
        )
    }

    private fun arrToCompanies(jsonArray: JsonArray): ArrayList<Company> {
        val productionCompanies = ArrayList<Company>()
        for (i in 0 until jsonArray.size()) {
            var logo = ""
            if (!jsonArray.get(i).asJsonObject.get("logo_path").isJsonNull)
                logo = jsonArray.get(i).asJsonObject.get("logo_path").asString

            val company = Company(
                jsonArray.get(i).asJsonObject.get("id").asInt,
                logo,
                jsonArray.get(i).asJsonObject.get("name").asString,
                jsonArray.get(i).asJsonObject.get("origin_country").asString
            )
            productionCompanies.add(company)
        }
        return productionCompanies
    }

    private fun arrToSeasons(jsonArray: JsonArray): ArrayList<Season> {
        val seasons = ArrayList<Season>()
        for (i in 0 until jsonArray.size()) {
            var poster = ""

            if (!jsonArray.get(i).asJsonObject.get("poster_path").isJsonNull)
                poster = jsonArray.get(i).asJsonObject.get("poster_path").asString

            val season = Season(
                jsonArray.get(i).asJsonObject.get("air_date").asString,
                jsonArray.get(i).asJsonObject.get("episode_count").asInt,
                jsonArray.get(i).asJsonObject.get("id").asInt,
                jsonArray.get(i).asJsonObject.get("name").asString,
                jsonArray.get(i).asJsonObject.get("overview").asString,
                poster,
                jsonArray.get(i).asJsonObject.get("season_number").asInt
            )
            seasons.add(season)
        }
        return seasons
    }

    private fun arrToGenres(jsonArray: JsonArray): ArrayList<Genre> {
        val genres = ArrayList<Genre>()
        for (i in 0 until jsonArray.size()) {
            val genre = Genre(
                jsonArray.get(i).asJsonObject.get("id").asInt,
                jsonArray.get(i).asJsonObject.get("name").asString,
            )
            genres.add(genre)
        }
        return genres
    }

    fun currencyString(number: Int): String{
        if (number == 0) return "-"
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(number)
        return "$ $formattedNumber"
    }

    fun yearStr(yyyymmdd: String): String {
        val timeArray = yyyymmdd.split("-".toRegex()).dropLastWhile { it.isEmpty() }
        return timeArray[0]
    }

}