package com.nizamalfian.androidjetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nizamalfian.androidjetpack.data.local.LocalRepository
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.data.local.entity.MovieWithGenres
import com.nizamalfian.androidjetpack.data.remote.RemoteRepository
import com.nizamalfian.androidjetpack.data.vo.Resource
import com.nizamalfian.androidjetpack.utils.*
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by L
 *
 *
 * on 7/9/2019
 */
class MovieRepositoryTest{
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val local = mock(LocalRepository::class.java)
    private val remote = mock(RemoteRepository::class.java)
    private val instantAppExecutors:InstantAppExecutors=mock(InstantAppExecutors::class.java)
    private val movieRepository=FakeMovieRepository(local,remote,instantAppExecutors)
    private val movieResponses= getDummyRemoteMovies()
    private val movieId=movieResponses[0].id

    @Test
    fun testGetAllMovies(){
        val dummmyMovies = MutableLiveData<List<MovieEntity>>().also{
            it.value= getDummyLocalMovies()
        }
        `when`(local.getAllMovies()).thenReturn(dummmyMovies)
        val result : Resource<List<MovieEntity>> = LiveDataTestUtil.getValue(movieRepository.getAllMovies())
        verify(local).getAllMovies()
        assertNotNull(result.data)
    }

    @Test
    fun testGetBookmarkedMovies(){
        val dataSourceFactory:DataSource.Factory<Int, MovieEntity> = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getBookmarkedMoviesAsPaged()).thenReturn(dataSourceFactory)
        movieRepository.getBookmarkedMoviesAsPaged()
        verify(local).getBookmarkedMoviesAsPaged()
    }

    @Test
    fun testGetAllTVShows(){
        val dummyTVShows = MutableLiveData<List<MovieEntity>>().also{
            it.value= getDummyLocalMovies()
        }
        `when`(local.getAllTVShows()).thenReturn(dummyTVShows)
        val result : Resource<List<MovieEntity>> = LiveDataTestUtil.getValue(movieRepository.getAllTVShows())
        verify(local).getAllTVShows()
        assertNotNull(result.data)
    }

    @Test
    fun testGetBookmarkedTVShows(){
        val dataSourceFactory:DataSource.Factory<Int, MovieEntity> = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getBookmarkedTVShowsAsPaged()).thenReturn(dataSourceFactory)
        movieRepository.getBookmarkedTVShowsAsPaged()
        verify(local).getBookmarkedTVShowsAsPaged()
    }

    @Test
    fun getMovieWithGenres(){
        val dummyEntity = MutableLiveData<MovieWithGenres>().also{
            it.value= getGenerateDummyMovieWithGenres(getDummyLocalMovie(),true)
        }
        `when`(local.getMovieWithGenres(movieId)).thenReturn(dummyEntity)
        val result : Resource<MovieWithGenres> = LiveDataTestUtil.getValue(movieRepository.getMovieWithGenre(movieId))
        verify(local).getMovieWithGenres(movieId)
        assertNotNull(result)
    }
}