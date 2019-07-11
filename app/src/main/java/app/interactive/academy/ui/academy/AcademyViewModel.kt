package app.interactive.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class AcademyViewModel(private val academyRepository: AcademyRepository):ViewModel() {
    fun getCourses():LiveData<ArrayList<CourseEntity>> = academyRepository.getAllCourses()
//    fun getCourses():List<CourseEntity> = generateDummyCourses()
}