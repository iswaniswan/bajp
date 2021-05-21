package com.iswan.main.movflix.data.source

import com.iswan.main.movflix.data.source.remote.rest.ApiMovies

class RemoteDataSource constructor(
    private val apiMovies: ApiMovies
) : IRemoteDataSource {

    override suspend fun getMovies() = apiMovies.getTrendingMoviesO()

    override suspend fun getTvShows() = apiMovies.getTrendingTvShowsO()

    override suspend fun getMovie(id: String) = apiMovies.getMovieO(id)

    override suspend fun getTvShow(id: String) = apiMovies.getTvShowO(id)

}

