package com.iswan.main.movflix.di

import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.data.source.remote.rest.ApiClient
import com.iswan.main.movflix.data.source.remote.rest.ApiMovies

object Injection {

    private fun provideApi(): ApiMovies = ApiMovies(ApiClient)

    private fun provideRemoteDataSource(): RemoteDataSource {
        val apiMovies = provideApi()
        return RemoteDataSource(apiMovies)
    }

    fun provideRepository() : Repository {
        val remoteDataSource = provideRemoteDataSource()
        return Repository.getInstance(remoteDataSource)
    }

}