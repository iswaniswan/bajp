package com.iswan.main.movflix.utils

import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity

object DummyEntities {

    private val movieEntity: MovieEntity =
        MovieEntity(
            "460465",
            false,
            "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
            20000000,
            DummyModels.sampleGenresMovie,
            "https://www.mortalkombatmovie.net",
            "en",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            3345.294,
            "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            DummyModels.sampleCompaniesMovie,
            "2021-04-07",
            50115000,
            110,
            "Released",
            "Get over here.",
            "Mortal Kombat",
            7.7,
            2440,
            false
        )

    private val tvShowEntity: TvShowEntity =
        TvShowEntity(
            "71712",
            "/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg",
            43,
            "2017-09-25",
            DummyModels.sampleGenresTvShow,
            "http://abc.go.com/shows/the-good-doctor",
            "2021-05-10",
            "The Good Doctor",
            4,
            "en",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            1045.03,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            DummyModels.sampleCompaniesTvShow,
            DummyModels.sampleSeasons,
            "Returning Series",
            "His mind is a mystery, his methods are a miracle.",
            8.6,
            8368,
            false
        )

    fun generateMovieEntities(): List<MovieEntity> {
        val entities = ArrayList<MovieEntity>()
        for (i in  0..19){
            entities.add(movieEntity)
        }
        return entities
    }

    fun generateTvShowEntities(): List<TvShowEntity> {
        val entities = ArrayList<TvShowEntity>()
        for (i in  0..19){
            entities.add(tvShowEntity)
        }
        return entities
    }
}