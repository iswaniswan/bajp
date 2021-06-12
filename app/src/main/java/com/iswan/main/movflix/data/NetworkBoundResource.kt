package com.iswan.main.movflix.data

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.Mapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.Exception

@Suppress("UNCHECKED_CAST")
abstract class NetworkBoundResource<ResultType, RequestType>{

    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        try {
            val dbSource = loadFromDB().firstOrNull()
            if (shouldFetch(dbSource)) {
                emit(Resource.Loading())
                when(val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDB().map {
                            Resource.Success(it)
                        })
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(Resource.Error<ResultType>(apiResponse.message))
                    }

                    is ApiResponse.Empty -> {
                        emitAll(loadFromDB().map { Resource.Success(it) })
                    }
                }
            } else {
                emitAll(loadFromDB().map { Resource.Success(it) })
            }
        } catch (e: Exception) {
            println(e.message)
        }
    } as Flow<Resource<ResultType>>

    protected abstract fun loadFromDB(): Flow<ResultType?>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected open fun onFetchFailed() { }

    fun asFlow(): Flow<Resource<ResultType>> = result

}