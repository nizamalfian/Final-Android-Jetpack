package com.nizamalfian.androidjetpack.di

import android.app.Application
import com.nizamalfian.androidjetpack.data.MovieRepository
import com.nizamalfian.androidjetpack.data.local.LocalRepository
import com.nizamalfian.androidjetpack.data.local.room.MovieDatabase
import com.nizamalfian.androidjetpack.data.remote.JSONHelper
import com.nizamalfian.androidjetpack.data.remote.RemoteRepository
import com.nizamalfian.androidjetpack.utils.AppExecutors

/**
 * Created by L
 *
 * on 7/8/2019
 */
class Injection {
    companion object{
        fun provideRepository(application: Application):MovieRepository{
            return MovieRepository.getInstance(
                LocalRepository(
                    MovieDatabase.getInstance(
                        application
                    ).academyDao()
                ),
                RemoteRepository.getInstance(
                    JSONHelper(
                        application
                    )
                ),
                AppExecutors()
            )
        }
    }
}