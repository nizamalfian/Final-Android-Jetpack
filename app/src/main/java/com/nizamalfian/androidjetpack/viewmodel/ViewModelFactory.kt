package com.nizamalfian.androidjetpack.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.di.Injection
import com.nizamalfian.androidjetpack.ui.reader.CourseReaderViewModel
import com.nizamalfian.androidjetpack.ui.bookmark.BookmarkViewModel
import com.nizamalfian.androidjetpack.ui.detail_.DetailCourseViewModel
import com.nizamalfian.androidjetpack.ui.academy.AcademyViewModel



/**
 * Created by L
 *
 * on 7/8/2019
 */
class ViewModelFactory(private val academyRepository: MovieRepository):ViewModelProvider.NewInstanceFactory() {
    companion object{
        private var INSTANCE:ViewModelFactory?=null
        fun getInstance(application: Application):ViewModelFactory{
            if(INSTANCE==null){
                synchronized(ViewModelFactory::class.java){
                    if(INSTANCE==null){
                        INSTANCE= ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(academyRepository) as T
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> TVShowViewModel(academyRepository) as T
            modelClass.isAssignableFrom(BookmarkedMovieViewModel::class.java) -> BookmarkedMovieViewModel(academyRepository) as T
            modelClass.isAssignableFrom(BookmarkedTVShowViewModel::class.java) -> BookmarkedTVShowViewModel(academyRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }
}