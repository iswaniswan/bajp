package com.iswan.main.movflix.data.rest

import com.google.gson.JsonElement
import com.iswan.main.movflix.data.models.MovieEntity
import com.iswan.main.movflix.data.models.TvShowEntity


val JsonElement.asTvShow: TvShowEntity
    get() {
        return TvShowEntity(
            asJsonObject.get("id").asInt,
            asJsonObject.get("poster_path").asString,
            asJsonObject.get("overview").asString,
            asJsonObject.get("first_air_date").asString,
            asJsonObject.get("name").asString,
            asJsonObject.get("vote_average").asDouble
        )
    }

val JsonElement.asMovie: MovieEntity
    get() {
        return MovieEntity(
            asJsonObject.get("id").asInt,
            asJsonObject.get("poster_path").asString,
            asJsonObject.get("overview").asString,
            asJsonObject.get("release_date").asString,
            asJsonObject.get("title").asString,
            asJsonObject.get("vote_average").asDouble
        )
    }

class Extensions {
}