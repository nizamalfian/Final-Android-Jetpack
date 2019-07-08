package app.interactive.academy.di

import android.app.Application
import android.util.Log
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.remote.JSONHelper
import app.interactive.academy.data.source.remote.RemoteRepository

/**
 * Created by L
 *
 * on 7/8/2019
 */
class Injection {
    companion object{
        fun provideRepository(application: Application):AcademyRepository{
            Log.d("parsing","Injection ${application==null}")
            return AcademyRepository.getInstance(
                LocalRepository(),
                RemoteRepository.getInstance(JSONHelper(application))
            )
        }
    }
}