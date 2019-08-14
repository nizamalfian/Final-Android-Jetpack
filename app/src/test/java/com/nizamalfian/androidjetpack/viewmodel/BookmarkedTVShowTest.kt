package com.nizamalfian.androidjetpack.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nizamalfian.androidjetpack.data.MovieRepository
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
class BookmarkedTVShowTest {

    private lateinit var viewModel: BookmarkedTVShowViewModel
    private val academyRepository= Mockito.mock(MovieRepository::class.java)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = BookmarkedTVShowViewModel(academyRepository)
    }

    @Test
    fun testGetCourses(){
        val bookmarkedMovies= MutableLiveData<Resource<PagedList<MovieEntity>>>().also{
            val pagedList: PagedList<MovieEntity> = Mockito.mock(PagedList::class.java) as PagedList<MovieEntity>
            it.value= Resource.success(pagedList)
        }
        Mockito.`when`(academyRepository.getBookmarkedTVShowsAsPaged()).thenReturn(bookmarkedMovies)
        val observer: Observer<Resource<PagedList<MovieEntity>>> = Mockito.mock(Observer::class.java) as Observer<Resource<PagedList<MovieEntity>>>
        viewModel.getBookmarks().observeForever(observer)
        Mockito.verify(academyRepository).getBookmarkedTVShowsAsPaged()
    }

}