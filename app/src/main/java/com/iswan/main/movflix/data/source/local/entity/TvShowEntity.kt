package com.iswan.main.movflix.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.data.models.Genre
import com.iswan.main.movflix.data.models.Season


@Entity(tableName = "tvshows")
data class TvShowEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "episodeRunTime")
    var episodeRunTime: Int,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "genres")
    var genres: List<Genre>,

    @ColumnInfo(name = "homepage")
    var homepage: String,

    @ColumnInfo(name = "lastAirDate")
    var lastAirDate: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "numberOfSeasons")
    var numberOfSeasons: Int,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "productionCompanies")
    var productionCompanies: List<Company>,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "seasons")
    var seasons: List<Season>,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "tagline")
    var tagline: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean
)
