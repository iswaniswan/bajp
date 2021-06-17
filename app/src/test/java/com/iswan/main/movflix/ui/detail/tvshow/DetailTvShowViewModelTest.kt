package com.iswan.main.movflix.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.cash.turbine.test
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.DummyEntities
import com.iswan.main.movflix.utils.DummyModels
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val dummyTvDetail = DummyModels.generateTvShowDetail()
    private val dummyEntity = DummyEntities.generateTvShowEntities()[0]

    private lateinit var showViewModel: DetailTvShowViewModel

    @Mock
    private lateinit var observer: Observer<Resource<TvShowDetail>>

    @Before
    fun setup() {
        showViewModel = DetailTvShowViewModel(repository, dispatcher)
    }

    @Test
    fun getTvShow() = runBlockingTest {
        val _tvShow = MutableLiveData<Resource<TvShowDetail>>()
        val tvShow : LiveData<Resource<TvShowDetail>> = _tvShow

        val tvShowDetail = flow {
            emit(Resource.Success(DummyModels.generateTvShowDetail()))
        }.flowOn(dispatcher)

        withContext(dispatcher) {
            Mockito.`when`(repository.getTvShow(anyString())).thenReturn(tvShowDetail)
            showViewModel.getTvShow("123456")
            Mockito.verify(repository).getTvShow(anyString())

            tvShowDetail.test {
                val result = expectItem()
                _tvShow.postValue(result)

                tvShow.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                assertEquals(result.data?.id, tvShow.value?.data?.id)

                expectComplete()
            }
        }
    }

    @Test
    fun insertUpdateFavourite() = runBlockingTest {
        doReturn(null).`when`(repository).insertUpdateFavouriteTvShow(dummyEntity)
        showViewModel.insertUpdateFavourite(dummyTvDetail)
        Mockito.verify(repository).insertUpdateFavouriteTvShow(dummyEntity)
    }
}