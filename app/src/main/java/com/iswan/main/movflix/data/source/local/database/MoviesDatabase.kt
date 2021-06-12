package com.iswan.main.movflix.data.source.local.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iswan.main.movflix.data.source.local.entity.*

@Database(entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}