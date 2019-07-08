package app.interactive.academy.ui.viewmodel

import android.app.Application
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
        @Volatile private lateinit var INSTANCE:ViewModelFactory
        fun getInstance(application: Application):ViewModelFactory{
            if(INSTANCE==null){
                synchronized(ViewModelFactory::class.java){
                    if(INSTANCE==null){
                        INSTANCE= ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE
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