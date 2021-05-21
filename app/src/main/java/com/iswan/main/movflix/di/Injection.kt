package com.iswan.main.movflix.di

import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.data.source.remote.rest.ApiClient
import com.iswan.main.movflix.data.source.remote.rest.ApiMovies

object Injection {

    fun provideApi(): ApiMovies = ApiMovies(ApiClient)

    fun provideRemoteDataSource(): RemoteDataSource {
        val apiMovies = provideApi()
        return RemoteDataSource(apiMovies)
    }

    fun provideRepository() : Repository {
        val remoteDataSource = provideRemoteDataSource()
        return Repository.getInstance(remoteDataSource)
    }

}