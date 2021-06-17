package com.iswan.main.movflix.di

import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.source.local.LocalDataSource
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): LocalDataSource =
        LocalDataSource(movieDao)

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: LocalDataSource,
        apiService: ApiService,
        dispatcher: CoroutineDispatcher
    ): Repository = Repository(localDataSource, apiService, dispatcher)
}