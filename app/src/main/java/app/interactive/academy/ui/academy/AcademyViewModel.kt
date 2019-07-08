package app.interactive.academy.ui.academy

import androidx.lifecycle.ViewModel
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.utils.generateDummyCourses

/**
 * Created by L
 *
 * on 6/29/2019
 */
class AcademyViewModel:ViewModel() {
    fun getCourses():List<CourseEntity> = generateDummyCourses()
}