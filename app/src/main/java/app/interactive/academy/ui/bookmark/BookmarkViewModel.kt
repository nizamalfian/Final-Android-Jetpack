package app.interactive.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class BookmarkViewModel(private val academyRepository: AcademyRepository):ViewModel() {
    fun getBookmarks():LiveData<Resource<List<CourseEntity>>> = academyRepository.getBookmarkedCourses()

    fun setBookmark(courseEntity:CourseEntity){
        val newState=!courseEntity.isBookmarked
        academyRepository.setCourseBookmark(courseEntity,newState)
    }
}