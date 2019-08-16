package com.nizamalfian.androidjetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.nizamalfian.androidjetpack.data.local.entity.*
import com.nizamalfian.androidjetpack.data.vo.Resource

/**
 * Created by L
 *
 * on 7/8/2019
 */
interface MovieDataSource {
    fun getAllCourses():LiveData<Resource<List<CourseEntity>>>
    fun getAllMovies():LiveData<Resource<List<MovieEntity>>>
    fun getAllMoviesAsPaged():LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTVShows():LiveData<Resource<List<MovieEntity>>>
    fun getCourseWithModule(courseId:String):LiveData<Resource<CourseWithModule>>
    fun getMovieWithGenre(movieId:Int):LiveData<Resource<MovieWithGenres>>
    fun getAllModulesByCourse(courseId:String):LiveData<Resource<List<ModuleEntity>>>
    fun getAllGenresByMovie(movieId:Int):LiveData<Resource<List<GenreEntity>>>
    fun getBookmarkedCoursesAsPaged():LiveData<Resource<PagedList<CourseEntity>>>
    fun getBookmarkedMoviesAsPaged():LiveData<Resource<PagedList<MovieEntity>>>
    fun getBookmarkedTVShowsAsPaged():LiveData<Resource<PagedList<MovieEntity>>>
    fun getContent(moduleId:String):LiveData<Resource<ModuleEntity>>
    fun setCourseBookmark(course: CourseEntity, state:Boolean)
    fun setMovieBookmark(movie: MovieEntity, state:Boolean)
    fun setReadModule(module: ModuleEntity)
}