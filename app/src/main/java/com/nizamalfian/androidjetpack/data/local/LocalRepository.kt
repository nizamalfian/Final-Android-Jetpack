package com.nizamalfian.androidjetpack.data.local

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nizamalfian.androidjetpack.data.local.entity.*
import com.nizamalfian.androidjetpack.data.local.room.MovieDao
import com.nizamalfian.androidjetpack.ui.movie.MovieFragment

/**
 * Created by L
 *
 * on 7/8/2019
 */
class LocalRepository(private val movieDao: MovieDao) {
    companion object{
        private var INSTANCE: LocalRepository?=null
        fun getInstance(movieDao: MovieDao): LocalRepository {
            if(INSTANCE ==null){
                INSTANCE =
                    LocalRepository(movieDao)
            }
            return INSTANCE as LocalRepository
        }
    }

    fun getAllMovies():LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getAllTVShows():LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getBookmarkedMovies():LiveData<List<MovieEntity>> = movieDao.getBookmarkedMovies()

    fun getBookmarkedMoviesAsPaged():DataSource.Factory<Int, MovieEntity> = movieDao.getBookmarkedMoviesAsPaged()

    fun getBookmarkedTVShowsAsPaged():DataSource.Factory<Int, MovieEntity> = movieDao.getBookmarkedTVShowsAsPaged()

    fun getMovieWithGenres(movieId:Int):LiveData<MovieWithGenres> = movieDao.getMovieWithGenresById(movieId)

    fun getAllGenresByMovie(movieId:Int):LiveData<List<GenreEntity>> = movieDao.getGenresByMovieId(movieId)

    fun insertMovies(movies:List<MovieEntity>){
        Log.d(MovieFragment.TAG+"_insertMovies",movies.size.toString())
        movieDao.insertMovies(movies).also {
            it.forEach { insertResult->
                Log.d(MovieFragment.TAG+"_also",insertResult.toString())
            }
        }
    }

    fun insertGenres(genres:List<GenreEntity>){
        movieDao.insertGenres(genres)
    }

    fun setMovieBookmark(movie: MovieEntity, newState:Boolean){
        movie.isBookmarked=newState
        movieDao.updateMovie(movie)
    }

    fun getGenresById(genreId:String):LiveData<GenreEntity> = movieDao.getGenresById(genreId)

    /*---------------------*/

    fun getAllCourses():LiveData<List<CourseEntity>> = movieDao.getCourses()

    fun getBookmarkedCourses():LiveData<List<CourseEntity>> = movieDao.getBookmarkedCourses()

    fun getBookmarkedCoursesAsPaged():DataSource.Factory<Int, CourseEntity> = movieDao.getBookmarkedCoursesAsPaged()

    fun getCourseWithModules(courseId:String):LiveData<CourseWithModule> = movieDao.getCourseWithModulesById(courseId)

    fun getAllModulesByCourse(courseId:String):LiveData<List<ModuleEntity>> = movieDao.getModulesByCourseId(courseId)

    fun insertCourses(courses:List<CourseEntity>){
        movieDao.insertCourses(courses)
    }

    fun insertModules(modules:List<ModuleEntity>){
        movieDao.insertModules(modules)
    }

    fun setCourseBookmark(course: CourseEntity, newState:Boolean){
        course.isBookmarked=newState
        movieDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId:String):LiveData<ModuleEntity> = MutableLiveData<ModuleEntity>()

    fun updateContent(content:String,moduleId:String){
        movieDao.updateModuleByContent(content,moduleId)
    }

    fun setReadModule(module: ModuleEntity){
        module.isRead=true
        movieDao.updateModule(module)
    }
}