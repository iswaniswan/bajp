package com.iswan.main.movflix.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.*
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.data.models.Genre

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    var idx: Int,

    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String,

    @ColumnInfo(name = "budget")
    var budget: Int,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "genres")
    var genres: ArrayList<Genre>,

    @ColumnInfo(name = "homepage")
    var homepage: String,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String,

    @ColumnInfo(name = "originalTitle")
    var originalTitle: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "popularity")
    var popularity: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "productionCompanies")
    var productionCompanies: ArrayList<Company>,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "revenue")
    var revenue: Int,

    @ColumnInfo(name = "runtime")
    var runtime: Int,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "tagline")
    var tagline: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean
)