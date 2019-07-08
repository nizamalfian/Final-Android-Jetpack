package app.interactive.academy.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.di.Injection
import app.interactive.academy.ui.reader.CourseReaderViewModel
import app.interactive.academy.ui.bookmark.BookmarkViewModel
import app.interactive.academy.ui.detail.DetailCourseViewModel
import app.interactive.academy.ui.academy.AcademyViewModel



/**
 * Created by L
 *
 * on 7/8/2019
 */
class ViewModelFactory(private val academyRepository: AcademyRepository):ViewModelProvider.NewInstanceFactory() {
    companion object{
        private var INSTANCE:ViewModelFactory?=null
        fun getInstance(application: Application):ViewModelFactory{
            Log.d("parsing_vmf","viewModelFactory ${application==null}")
            if(INSTANCE==null){
                Log.d("parsing","viewModelFactory_0 ${application==null}")
                synchronized(ViewModelFactory::class.java){
                    Log.d("parsing","viewModelFactory_1 ${application==null}")
                    if(INSTANCE==null){
                        Log.d("parsing","viewModelFactory_2 ${application==null}")
                        INSTANCE= ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            Log.d("parsing","viewModelFactory_3 ${application==null}")
            return INSTANCE as ViewModelFactory
        }
    }

    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> AcademyViewModel(academyRepository) as T
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> DetailCourseViewModel(academyRepository) as T
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> BookmarkViewModel(academyRepository) as T
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> CourseReaderViewModel(academyRepository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }
}