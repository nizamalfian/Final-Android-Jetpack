package app.interactive.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class BookmarkViewModel(private val academyRepository: AcademyRepository):ViewModel() {
    fun getBookmarks():List<CourseEntity> = academyRepository.getBookmarkedCourses()
//    fun getBookmarks():List<CourseEntity> = generateDummyCourses()
}