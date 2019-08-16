package com.nizamalfian.androidjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.utils.getDummyLocalTVShows
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
class TVShowViewModelTest {
    private val username="nizamalfian"
    private lateinit var viewModel: TVShowViewModel
    private val academyRepository= Mockito.mock(MovieRepository::class.java)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = TVShowViewModel(academyRepository)
    }

    @Test
    fun testGetCourse(){
        val dummyTVShows= MutableLiveData<Resource<List<MovieEntity>>>().also {
            it.value= Resource.success(getDummyLocalTVShows())
        }
        Mockito.`when`(academyRepository.getAllTVShows()).thenReturn(dummyTVShows)
        val observer : Observer<Resource<List<MovieEntity>>> = Mockito.mock(Observer::class.java) as Observer<Resource<List<MovieEntity>>>
        viewModel.setUsername(username)
        viewModel.getTVShows().observeForever(observer)
        Mockito.verify(academyRepository).getAllTVShows()
    }
}