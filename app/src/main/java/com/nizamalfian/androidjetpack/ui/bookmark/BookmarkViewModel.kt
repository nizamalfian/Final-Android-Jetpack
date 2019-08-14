package com.nizamalfian.androidjetpack.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.entity.CourseEntity
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 6/29/2019
 */
class BookmarkViewModel(private val academyRepository: MovieRepository):ViewModel() {
    fun getBookmarks():LiveData<Resource<PagedList<CourseEntity>>> = academyRepository.getBookmarkedCoursesAsPaged()

    fun setBookmark(courseEntity: CourseEntity){
        val newState=!courseEntity.isBookmarked
        academyRepository.setCourseBookmark(courseEntity,newState)
    }
}