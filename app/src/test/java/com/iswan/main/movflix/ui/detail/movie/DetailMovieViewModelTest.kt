package com.iswan.main.movflix.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.cash.turbine.test
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.MovieDetail
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
class DetailMovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val dummyMovieDetail = DummyModels.generateMovieDetail()
    private val dummyEntity = DummyEntities.generateMovieEntities()[0]

    private lateinit var viewModel: DetailMovieViewModel

    @Mock
    private lateinit var observer: Observer<Resource<MovieDetail>>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(repository, dispatcher)
    }

    @Test
    fun getMovie() = runBlockingTest {
        val _movie = MutableLiveData<Resource<MovieDetail>>()
        val movie : LiveData<Resource<MovieDetail>> = _movie

        val movieDetail = flow {
            emit(Resource.Success(DummyModels.generateMovieDetail()))
        }.flowOn(dispatcher)

        withContext(dispatcher) {
            Mockito.`when`(repository.getMovie(anyString())).thenReturn(movieDetail)
            viewModel.getMovie("123456")
            Mockito.verify(repository).getMovie(anyString())

            movieDetail.test {
                val result = expectItem()
                _movie.postValue(result)

                movie.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                assertEquals(result.data?.id, movie.value?.data?.id)

                expectComplete()
            }
        }
    }

    @Test
    fun insertUpdateFavourite() = runBlockingTest {
        doReturn(null).`when`(repository).insertUpdateFavouriteMovie(dummyEntity)
        viewModel.insertUpdateFavourite(dummyMovieDetail)
        Mockito.verify(repository).insertUpdateFavouriteMovie(dummyEntity)
    }
}