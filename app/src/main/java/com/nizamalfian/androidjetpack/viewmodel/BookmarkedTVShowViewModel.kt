package com.nizamalfian.androidjetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.MovieEntity
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 8/13/2019
 */
class BookmarkedTVShowViewModel(private val academyRepository: MovieRepository): ViewModel() {
    fun getBookmarks(): LiveData<Resource<PagedList<MovieEntity>>> = academyRepository.getBookmarkedTVShowsAsPaged()

    fun setBookmark(movieEntity: MovieEntity){
        val newState=!movieEntity.isBookmarked
        academyRepository.setMovieBookmark(movieEntity,newState)
    }
}