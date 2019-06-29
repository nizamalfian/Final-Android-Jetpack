package app.interactive.academy.viewmodel

import androidx.lifecycle.ViewModel
import app.interactive.academy.data.CourseEntity
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class AcademyViewModel:ViewModel() {
    fun getCourses():List<CourseEntity> = generateDummyCourses()
}