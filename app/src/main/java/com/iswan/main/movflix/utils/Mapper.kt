package com.iswan.main.movflix.utils

import com.iswan.main.movflix.data.models.*
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.data.source.remote.response.*

object Mapper {

    private val Int?.safe: Int get() = (this ?: 0)

    private val String?.safe: String get() = (this ?: "")

    private val Double?.safe: Double get() = (this ?: 0.0)

    private val Long?.safe: Long get() = (this ?: 0)

    private val List<GenresItem>?.toGenres: ArrayList<Genre>
        get() {
            val list = ArrayList<Genre>()
            this?.map {
                list.add(
                    Genre(it.id, it.name)
                )
            }
            return list
        }

    private val List<ProductionCompaniesItem>?.toCompanies: ArrayList<Company>
        get() {
            val list = ArrayList<Company>()
            this?.map {
                list.add(
                    Company(
                        it.id, it.logoPath.safe, it.name, it.originCountry.safe
                    )
                )
            }
            return list
        }

    private val List<SeasonsItem>?.toSeasons: ArrayList<Season>
        get() {
            val seasons = ArrayList<Season>()
            this?.map {
                seasons.add(
                    Season(
                        it.airDate.safe,
                        it.episodeCount,
                        it.id,
                        it.name,
                        it.overview.safe,
                        it.posterPath.safe,
                        it.seasonNumber
                    )
                )
            }
            return seasons
        }

    fun responseToModel(movie: MovieResponse): Movie {
        return Movie(
            movie.id.toString(),
            movie.posterPath.safe,
            movie.overview.safe,
            movie.releaseDate.safe,
            movie.title.safe,
            movie.voteAverage.safe
        )
    }

    fun responseToModel(tvShow: TvShowResponse): TvShow {
        return TvShow(
            tvShow.id.toString(),
            tvShow.posterPath.safe,
            tvShow.overview.safe,
            tvShow.firstAirDate.safe,
            tvShow.name.safe,
            tvShow.voteAverage.safe
        )
    }

    fun responseToModel(mDetail: MovieDetailResponse): MovieDetail {
        return MovieDetail(
            mDetail.adult,
            mDetail.backdropPath.safe,
            mDetail.budget.safe,
            mDetail.genres.toGenres,
            mDetail.homepage.safe,
            mDetail.id.toString(),
            mDetail.originalLanguage.safe,
            mDetail.originalTitle.safe,
            mDetail.overview.safe,
            mDetail.popularity.safe,
            mDetail.posterPath.safe,
            mDetail.productionCompanies.toCompanies,
            mDetail.releaseDate.safe,
            mDetail.revenue.safe,
            mDetail.runtime.safe,
            mDetail.status.safe,
            mDetail.tagline.safe,
            mDetail.title.safe,
            mDetail.voteAverage.safe,
            mDetail.voteCount.safe,
            false
        )
    }

    fun responseToModel(tDetail: TvShowDetailResponse): TvShowDetail {
        return TvShowDetail(
            tDetail.backdropPath.safe,
            tDetail.episodeRunTime?.get(0).safe,
            tDetail.firstAirDate.safe,
            tDetail.genres.toGenres,
            tDetail.homepage.safe,
            tDetail.id.toString(),
            tDetail.lastAirDate.safe,
            tDetail.name.safe,
            tDetail.numberOfSeasons.safe,
            tDetail.originalLanguage.safe,
            tDetail.overview.safe,
            tDetail.popularity.safe,
            tDetail.posterPath.safe,
            tDetail.productionCompanies.toCompanies,
            tDetail.seasons.toSeasons,
            tDetail.status.safe,
            tDetail.tagline.safe,
            tDetail.voteAverage.safe,
            tDetail.voteCount.safe,
            false
        )
    }

    fun modelToEntity(movie: MovieDetail): MovieEntity {
        return MovieEntity(
            movie.id,
            movie.adult,
            movie.backdropPath,
            movie.budget,
            movie.genres,
            movie.homepage,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.productionCompanies,
            movie.releaseDate,
            movie.revenue,
            movie.runtime,
            movie.status,
            movie.tagline,
            movie.title,
            movie.voteAverage,
            movie.voteCount,
            movie.isFavourite,
        )
    }

    fun modelToEntity(tvshow: TvShowDetail): TvShowEntity {
        return TvShowEntity(
            tvshow.id,
            tvshow.backdropPath,
            tvshow.episodeRunTime,
            tvshow.firstAirDate,
            tvshow.genres,
            tvshow.homepage,
            tvshow.lastAirDate,
            tvshow.name,
            tvshow.numberOfSeasons,
            tvshow.originalLanguage,
            tvshow.overview,
            tvshow.popularity,
            tvshow.posterPath,
            tvshow.productionCompanies,
            tvshow.seasons,
            tvshow.status,
            tvshow.tagline,
            tvshow.voteAverage,
            tvshow.voteCount,
            tvshow.isFavourite
        )
    }

    fun entityToModel(movie: MovieEntity): Movie {
        return Movie(
            movie.id,
            movie.posterPath,
            movie.overview,
            movie.releaseDate,
            movie.title,
            movie.voteAverage
        )
    }

    fun entityToModelDetail(movie: MovieEntity): MovieDetail {
        return MovieDetail(
            movie.adult,
            movie.backdropPath,
            movie.budget,
            movie.genres,
            movie.homepage,
            movie.id,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.productionCompanies,
            movie.releaseDate,
            movie.revenue,
            movie.runtime,
            movie.status,
            movie.tagline,
            movie.title,
            movie.voteAverage,
            movie.voteCount,
            movie.isFavourite
        )
    }

    fun entityToModel(tvshow: TvShowEntity): TvShow {
        return TvShow(
            tvshow.id,
            tvshow.posterPath,
            tvshow.overview,
            tvshow.firstAirDate,
            tvshow.name,
            tvshow.voteAverage,
        )
    }

    fun entityToModelDetail(tvshow: TvShowEntity): TvShowDetail {
        return TvShowDetail(
            tvshow.backdropPath,
            tvshow.episodeRunTime,
            tvshow.firstAirDate,
            tvshow.genres,
            tvshow.homepage,
            tvshow.id,
            tvshow.lastAirDate,
            tvshow.name,
            tvshow.numberOfSeasons,
            tvshow.originalLanguage,
            tvshow.overview,
            tvshow.popularity,
            tvshow.posterPath,
            tvshow.productionCompanies,
            tvshow.seasons,
            tvshow.status,
            tvshow.tagline,
            tvshow.voteAverage,
            tvshow.voteCount,
            tvshow.isFavourite,
        )
    }

    fun responseToEntity(movie: MovieResponse): MovieEntity {
        return MovieEntity(
            movie.id.toString(),
            false,
            "",
            0,
            arrayListOf(),
            "",
            "",
            "",
            movie.overview.safe,
            0.0,
            movie.posterPath.safe,
            arrayListOf(),
            movie.releaseDate.safe,
            0,
            0,
            "",
            "",
            movie.title.safe,
            movie.voteAverage,
            0,
            false
        )
    }

    fun responseToEntity(tvshow: TvShowResponse): TvShowEntity {
        return TvShowEntity(
            tvshow.id.toString(),
            "",
            0,
            "",
            arrayListOf(),
            "",
            "",
            tvshow.name.safe,
            0,
            "",
            "",
            0.0,
            tvshow.posterPath.safe,
            arrayListOf(),
            arrayListOf(),
            "",
            "",
            tvshow.voteAverage,
            0,
            false,
        )
    }

    fun responseToEntity(movie: MovieDetailResponse): MovieEntity {
        return MovieEntity(
            movie.id.toString(),
            movie.adult,
            movie.backdropPath.safe,
            movie.budget.safe,
            movie.genres.toGenres,
            movie.homepage.safe,
            movie.originalLanguage.safe,
            movie.originalTitle.safe,
            movie.overview.safe,
            movie.popularity.safe,
            movie.posterPath.safe,
            movie.productionCompanies.toCompanies,
            movie.releaseDate.safe,
            movie.revenue.safe,
            movie.runtime.safe,
            movie.status.safe,
            movie.tagline.safe,
            movie.title.safe,
            movie.voteAverage.safe,
            movie.voteCount.safe,
            false
        )
    }

    fun responseToEntity(tvshow: TvShowDetailResponse): TvShowEntity {
        return TvShowEntity(
            tvshow.id.toString(),
            tvshow.backdropPath.safe,
            tvshow.episodeRunTime?.get(0).safe,
            tvshow.firstAirDate.safe,
            tvshow.genres.toGenres,
            tvshow.homepage.safe,
            tvshow.lastAirDate.safe,
            tvshow.name,
            tvshow.numberOfSeasons.safe,
            tvshow.originalLanguage.safe,
            tvshow.overview.safe,
            tvshow.popularity.safe,
            tvshow.posterPath.safe,
            tvshow.productionCompanies.toCompanies,
            tvshow.seasons.toSeasons,
            tvshow.status.safe,
            tvshow.tagline.safe,
            tvshow.voteAverage.safe,
            tvshow.voteCount.safe,
            false
        )
    }

}