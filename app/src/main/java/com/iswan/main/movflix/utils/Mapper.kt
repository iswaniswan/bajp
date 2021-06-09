package com.iswan.main.movflix.utils

import com.iswan.main.movflix.data.models.*
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.data.source.remote.response.*

object Mapper {

    private val Int.safe: Int get() = (this ?: 0)

    private val String?.safe: String get() = (this ?: "")

    private val Double.safe: Double get() = (this ?: 0.0)

    private val List<GenresItem>.toGenres: ArrayList<Genre>
        get() {
            val list = ArrayList<Genre>()
            this.map {
                list.add(
                    Genre(it.id, it.name)
                )
            }
            return list
        }

    private val List<ProductionCompaniesItem>.toCompanies: ArrayList<Company>
        get() {
            val list = ArrayList<Company>()
            this.map {
                list.add(
                    Company(
                        it.id, it.logoPath.safe, it.name, it.originCountry.safe
                    )
                )
            }
            return list
        }

    private val List<SeasonsItem>.toSeasons: ArrayList<Season>
        get() {
            val seasons = ArrayList<Season>()
            this.map {
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
            movie.id.safe,
            movie.posterPath.safe,
            movie.overview.safe,
            movie.releaseDate.safe,
            movie.title.safe,
            movie.voteAverage.safe
        )
    }

    fun responseToModel(tvShow: TvShowResponse): TvShow {
        return TvShow(
            tvShow.id.safe,
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
            mDetail.backdropPath,
            mDetail.budget.safe,
            mDetail.genres.toGenres,
            mDetail.homepage.safe,
            mDetail.id,
            mDetail.originalLanguage.safe,
            mDetail.originalTitle.safe,
            mDetail.overview.safe,
            mDetail.popularity,
            mDetail.posterPath.safe,
            mDetail.productionCompanies.toCompanies,
            mDetail.releaseDate.safe,
            mDetail.revenue.safe,
            mDetail.runtime.safe,
            mDetail.status.safe,
            mDetail.tagline.safe,
            mDetail.title.safe,
            mDetail.voteAverage,
            mDetail.voteCount,
            false
        )
    }

    fun responseToModel(tDetail: TvShowDetailResponse): TvShowDetail {
        return TvShowDetail(
            tDetail.backdropPath.safe,
            tDetail.episodeRunTime.get(0),
            tDetail.firstAirDate.safe,
            tDetail.genres.toGenres,
            tDetail.homepage.safe,
            tDetail.id,
            tDetail.lastAirDate.safe,
            tDetail.name.safe,
            tDetail.numberOfSeasons,
            tDetail.originalLanguage.safe,
            tDetail.overview.safe,
            tDetail.popularity,
            tDetail.posterPath.safe,
            tDetail.productionCompanies.toCompanies,
            tDetail.seasons.toSeasons,
            tDetail.status.safe,
            tDetail.tagline.safe,
            tDetail.voteAverage,
            tDetail.voteCount,
            false
        )
    }

    fun modelToEntity(movie: MovieDetail): MovieEntity {
        return MovieEntity(
            0,
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
            0,
            movie.id,
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
            tvshow.id,
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
            0,
            movie.id,
            movie.adult,
            movie.backdropPath,
            movie.budget,
            movie.genres.toGenres,
            movie.homepage,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.posterPath,
            movie.productionCompanies.toCompanies,
            movie.releaseDate,
            movie.revenue,
            movie.runtime,
            movie.status,
            movie.tagline,
            movie.title,
            movie.voteAverage,
            movie.voteCount,
            false
        )
    }

    fun responseToEntity(tvshow: TvShowDetailResponse): TvShowEntity {
        return TvShowEntity(
            tvshow.id,
            tvshow.backdropPath,
            tvshow.episodeRunTime[0],
            tvshow.firstAirDate,
            tvshow.genres.toGenres,
            tvshow.homepage,
            tvshow.lastAirDate,
            tvshow.name,
            tvshow.numberOfSeasons,
            tvshow.originalLanguage,
            tvshow.overview,
            tvshow.popularity,
            tvshow.posterPath,
            tvshow.productionCompanies.toCompanies,
            tvshow.seasons.toSeasons,
            tvshow.status,
            tvshow.tagline,
            tvshow.voteAverage,
            tvshow.voteCount,
            false
        )
    }

}