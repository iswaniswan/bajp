package com.iswan.main.movflix.data

import com.iswan.main.movflix.data.models.*
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
                        it.airDate,
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
            movie.poster_path.safe,
            movie.overview.safe,
            movie.release_date.safe,
            movie.title.safe,
            movie.vote_average.safe
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
            mDetail.voteCount
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
            tDetail.voteCount
        )
    }

}