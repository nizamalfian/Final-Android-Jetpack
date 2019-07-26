package app.interactive.academy.data

import androidx.lifecycle.LiveData
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Resource

/**
 * Created by L
 *
 * on 7/8/2019
 */
interface AcademyDataSource {
    fun getAllCourses():LiveData<Resource<ArrayList<CourseEntity>>>
    fun getCourseWithModule(courseId:String):LiveData<Resource<CourseEntity>>
    fun getAllModulesByCourse(courseId:String):LiveData<Resource<ArrayList<ModuleEntity>>>
    fun getBookmarkedCourses():LiveData<ArrayList<Resource<CourseEntity>>>
    fun getContent(courseId: String,moduleId:String):LiveData<Resource<ModuleEntity>>
    fun setCourseBookmark(course:CourseEntity,state:Boolean)
    fun setReadModule(module:ModuleEntity)
}