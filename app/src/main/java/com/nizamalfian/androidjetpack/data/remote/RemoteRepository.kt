package com.nizamalfian.androidjetpack.data.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nizamalfian.androidjetpack.data.remote.response.*
import com.nizamalfian.androidjetpack.utils.EspressoIdlingResource


/**
 * Created by L
 *
 * on 7/8/2019
 */
class RemoteRepository(private val jsonHelper: JSONHelper) {
    private val SERVICE_LATENCY_IN_MILLIS: Long = 2000

    companion object {
        private var INSTANCE: RemoteRepository? = null
        fun getInstance(jsonHelper: JSONHelper): RemoteRepository {
            if (INSTANCE == null)
                INSTANCE =
                    RemoteRepository(jsonHelper)
            return INSTANCE as RemoteRepository
        }
    }

    fun getAllMoviesAsLiveData():LiveData<ApiResponse<List<MovieResponse>>>{
        EspressoIdlingResource.increment()
        return MutableLiveData<ApiResponse<List<MovieResponse>>>().also{
            Handler().postDelayed({
                it.value=
                    ApiResponse.success(jsonHelper.loadMovies())
                EspressoIdlingResource.decrement()
            },SERVICE_LATENCY_IN_MILLIS)
        }
    }

    fun getAllTVShowsAsLiveData():LiveData<ApiResponse<List<MovieResponse>>>{
        EspressoIdlingResource.increment()
        return MutableLiveData<ApiResponse<List<MovieResponse>>>().also{
            Handler().postDelayed({
                it.value=
                    ApiResponse.success(jsonHelper.loadTVShows())
                EspressoIdlingResource.decrement()
            },SERVICE_LATENCY_IN_MILLIS)
        }
    }

    fun getAllCoursesAsLiveData():LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<CourseResponse>>>()
        Handler().postDelayed({
            resultCourse.value=
                ApiResponse.success(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultCourse
    }

    fun getAllGenresByMovieAsLiveData(movieId:Int):LiveData<ApiResponse<List<GenreResponse>>>{
        EspressoIdlingResource.increment()
        return MutableLiveData<ApiResponse<List<GenreResponse>>>().also{
            Handler().postDelayed({
                it.value= ApiResponse.success(
                    jsonHelper.loadGenresByMovie(movieId)
                )
            },SERVICE_LATENCY_IN_MILLIS)
        }
    }

    fun getAllModulesByCourseAsLiveData(courseId: String):LiveData<ApiResponse<List<ModuleResponse>>>{
        EspressoIdlingResource.increment()
        val resultModules = MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        Handler().postDelayed({
            resultModules.value=
                ApiResponse.success(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
        return resultModules
    }

    fun getContentAsLiveData(moduleId:String):LiveData<ApiResponse<ContentResponse?>>{
        EspressoIdlingResource.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse?>>()
        Handler().postDelayed({
            resultContent.value=
                ApiResponse.success(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        },SERVICE_LATENCY_IN_MILLIS)
        return resultContent
    }
}