package com.iswan.main.movflix.utils

import com.iswan.main.movflix.data.models.*

object DataDummy {

    private val sampleGenresMovie: ArrayList<Genre> = arrayListOf(
        Genre(28, "Action"),
        Genre(14, "Fantasy"),
        Genre(12, "Adventure")
    )

    private val sampleCompaniesMovie: ArrayList<Company> = arrayListOf(
        Company(76907, "/wChlWsVgwSd4ZWxTIm8PTEcaESz.png", "Atomic Monster", "US"),
        Company(8000, "/f8NwLg72BByt3eav7lX1lcJfe60.png", "Broken Road Productions", "US"),
        Company(12, "/iaYpEp3LQmb8AfAtmTvpqd4149c.png", "New Line Cinema", "US"),
        Company(174, "/IuAlhI9eVC9Z8UQWOIDdWRKSEJ.png", "Warner Bros. Pictures", "US"),
        Company(
            2806,
            "/vxOhCbpsRBh10m6LZ3HyImTYpPY.png",
            "South Australian Film Corporation",
            "AU"
        ),
        Company(13033, "", "NetherRealm Studios", "US")
    )

    fun getSampleMovieDetail(): MovieDetail =
        MovieDetail(
            false,
            "/6ELCZlTA5lGUops70hKdB83WJxH.jpg",
            20000000,
            sampleGenresMovie,
            "https://www.mortalkombatmovie.net",
            460465,
            "en",
            "Mortal Kombat",
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            3345.294,
            "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
            sampleCompaniesMovie,
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


    private val sampleGenresTvShow: ArrayList<Genre> = arrayListOf(
        Genre(18, "Drama")
    )

    private val sampleCompaniesTvShow: ArrayList<Company> = arrayListOf(
        Company(19366, "/vOH8dyQhLK01pg5fYkgiS31jlFm.png", "ABC Studios", "US"),
        Company(90375, "", "3AD", "US"),
        Company(11073, "/wHs44fktdoj6c378ZbSWfzKsM2Z.png", "Sony Pictures Television Studios", "US")
    )

    private val sampleSeasons: ArrayList<Season> = arrayListOf(
        Season("2017-09-25", 18, 88380, "Season 1", "", "/hiNyjUSTFrbMv3Sc0CxNW2o39az.jpg", 1),
        Season(
            "2018-09-24",
            18,
            107199,
            "Season 2",
            "Dr. Shaun Murphy’s world has begun to expand as he continues to work harder than he ever has before, navigating his new environment and relationships to prove to his colleagues at the prestigious St. Bonaventure Hospital’s surgical unit that his extraordinary medical gifts will save lives.",
            "/sIq4SFloM0JSeRNAQVqb5g5zAng.jpg",
            2
        ),
        Season(
            "2019-09-23",
            20,
            127097,
            "Season 3",
            "Dr. Shaun Murphy continues to use his extraordinary medical gifts at St. Bonaventure Hospital’s surgical unit. As his friendships deepen, Shaun tackles the world of dating for the first time and continues to work harder than he ever has before.",
            "/8QFssOwaWZ3eB60cGwDpfrZBscv.jpg",
            3
        ),
        Season(
            "2020-11-02",
            20,
            163383,
            "Season 4",
            "Dr. Shaun Murphy continues to use his extraordinary medical gifts at St. Bonaventure Hospital’s surgical unit. As his romantic relationship with Lea deepens, he will also face new responsibilities as a fourth-year resident when he is put in charge of supervising a new set of residents that will test him in ways he cannot predict. Meanwhile, the team must deal with the uncertainty and pressure that the COVID-19 pandemic brings now that it has hit their hospital.",
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            4
        )
    )

    fun getSampleTvShowDetail(): TvShowDetail =
        TvShowDetail(
            "/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg",
            43,
            "2017-09-25",
            sampleGenresTvShow,
            "http://abc.go.com/shows/the-good-doctor",
            71712,
            "2021-05-10",
            "The Good Doctor",
            4,
            "en",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            1045.03,
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            sampleCompaniesTvShow,
            sampleSeasons,
            "Returning Series",
            "His mind is a mystery, his methods are a miracle.",
            8.6,
            8368,
            false
        )

}