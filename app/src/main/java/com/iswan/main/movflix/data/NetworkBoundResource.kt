package com.iswan.main.movflix.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.asLiveData
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse

abstract class NetworkBoundResource<ResultType, RequestType>(private val POSITION: Int?) {

    private val TAG = "NetworkBoundResource"

    private val result = MediatorLiveData<ResultType>().apply {
            value = null
            val dbSource = loadFromDB()
            this.addSource(dbSource) {
                this.removeSource(dbSource)
                if (shouldFetch(it)) {
                    fetchFromNetwork(dbSource)
                } else {
                    this.addSource(dbSource) { newData ->
                        value = newData
                    }
                }
            }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when(response) {
                is ApiResponse.Success -> {
                    saveCallResult(response.data)
                    result.addSource(loadFromDB()) {
                        result.value = it
                    }
                }
                is ApiResponse.Empty -> {
                    result.addSource(loadFromDB()) {
                        result.value = it
                    }
                }
                is ApiResponse.Error -> {
                    result.addSource(dbSource) {
                        result.value = it
                    }
                }
            }
        }
    }

    protected abstract fun saveCallResult(data: RequestType)

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDB(): LiveData<ResultType>

    fun asLiveData(): LiveData<ResultType> = result

}