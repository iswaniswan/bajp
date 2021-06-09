package com.iswan.main.movflix.data.source.paging

import androidx.paging.PagingData
import com.iswan.main.movflix.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface PagingRepository {
    fun postsOfSubreddit(query: String, pageSize: Int): Flow<PagingData<Movie>>
}