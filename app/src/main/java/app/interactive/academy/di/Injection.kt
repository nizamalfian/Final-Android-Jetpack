package app.interactive.academy.di

import android.app.Application
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.room.AcademyDatabase
import app.interactive.academy.data.source.remote.JSONHelper
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.utils.AppExecutors

/**
 * Created by L
 *
 * on 7/8/2019
 */
class Injection {
    companion object{
        fun provideRepository(application: Application):AcademyRepository{
            return AcademyRepository.getInstance(
                LocalRepository(AcademyDatabase.getInstance(application).academyDao()),
                RemoteRepository.getInstance(JSONHelper(application)),
                AppExecutors()
            )
        }
    }
}