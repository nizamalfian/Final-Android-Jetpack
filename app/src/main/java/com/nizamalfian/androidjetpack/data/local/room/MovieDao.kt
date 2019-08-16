package com.nizamalfian.androidjetpack.data.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.nizamalfian.androidjetpack.data.local.entity.*

/**
 * Created by nizamalfian on 25/07/2019.
 */
@Dao
interface MovieDao {
    @WorkerThread
    @Query("SELECT * FROM CourseEntity")
    fun getCourses():LiveData<List<CourseEntity>>

    @WorkerThread
    @Query("SELECT * FROM MovieEntity where isTVShow=0")
    fun getMovies():LiveData<List<MovieEntity>>

    @WorkerThread
    @Query("SELECT * FROM MovieEntity where isTVShow=0")
    fun getMoviesAsPaged():DataSource.Factory<Int,MovieEntity>

    @WorkerThread
    @Query("SELECT * FROM MovieEntity where isTVShow=1")
    fun getTVShows():LiveData<List<MovieEntity>>

    @WorkerThread
    @Query("SELECT * FROM CourseEntity where isBookmarked = 1")
    fun getBookmarkedCourses():LiveData<List<CourseEntity>>

    @WorkerThread
    @Query("SELECT * FROM MOVIEENTITY where isBookmarked = 1")
    fun getBookmarkedMovies():LiveData<List<MovieEntity>>

    @Query("SELECT * FROM CourseEntity where isBookmarked=1")
    fun getBookmarkedCoursesAsPaged():DataSource.Factory<Int, CourseEntity>

    @Query("SELECT * FROM MovieEntity where isBookmarked=1 & isTVShow=0")
    fun getBookmarkedMoviesAsPaged():DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM MovieEntity where isBookmarked=1 & isTVShow=1")
    fun getBookmarkedTVShowsAsPaged():DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM CourseEntity WHERE courseId= :courseId")
    fun getCourseWithModulesById(courseId:String):LiveData<CourseWithModule>

    @Transaction
    @Query("SELECT * FROM MovieEntity WHERE movieId=:movieId")
    fun getMovieWithGenresById(movieId:Int):LiveData<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(courses:List<CourseEntity>):LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies:List<MovieEntity>):LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateCourse(course: CourseEntity):Int

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: MovieEntity):Int

    @Query("SELECT * FROM ModuleEntity WHERE courseId = :courseId")
    fun getModulesByCourseId(courseId:String):LiveData<List<ModuleEntity>>

    @Query("SELECT * FROM GenreEntity WHERE movieId = :movieId")
    fun getGenresByMovieId(movieId:Int):LiveData<List<GenreEntity>>

    @Query("SELECT * FROM ModuleEntity WHERE moduleId = :moduleId")
    fun getModuleById(moduleId:String):LiveData<ModuleEntity>

    @Query("SELECT * FROM GenreEntity WHERE genreId = :genreId")
    fun getGenresById(genreId:String):LiveData<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModules(modules:List<ModuleEntity>):LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(modules:List<GenreEntity>):LongArray

    @Update
    fun updateModule(module: ModuleEntity):Int

    @Update
    fun updateGenre(genre: GenreEntity):Int

    @Query("UPDATE ModuleEntity SET content = :content WHERE moduleId = :moduleId")
    fun updateModuleByContent(content:String,moduleId:String):Int

}