package com.nizamalfian.androidjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.utils.getDummyLocalMovies
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.data.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by L
 *
 * on 8/13/2019
 */
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private val username="nizamalfian"
    private lateinit var viewModel: MovieViewModel
    private val academyRepository= Mockito.mock(MovieRepository::class.java)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(academyRepository)
    }

    @Test
    fun testGetCourse(){
        val dummyMovies= MutableLiveData<Resource<List<MovieEntity>>>().also {
            it.value= Resource.success(getDummyLocalMovies())
        }
        Mockito.`when`(academyRepository.getAllMovies()).thenReturn(dummyMovies)
        val observer : Observer<Resource<List<MovieEntity>>> = Mockito.mock(Observer::class.java) as Observer<Resource<List<MovieEntity>>>
        viewModel.setUsername(username)
        viewModel.getMovies().observeForever(observer)
        Mockito.verify(academyRepository).getAllMovies()
    }
}