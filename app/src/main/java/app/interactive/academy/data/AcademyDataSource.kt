package app.interactive.academy.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Resource

/**
 * Created by L
 *
 * on 7/8/2019
 */
interface AcademyDataSource {
    fun getAllCourses():LiveData<Resource<List<CourseEntity>>>
    fun getCourseWithModule(courseId:String):LiveData<Resource<CourseWithModule>>
    fun getAllModulesByCourse(courseId:String):LiveData<Resource<List<ModuleEntity>>>
    fun getBookmarkedCoursesAsPaged():LiveData<Resource<PagedList<CourseEntity>>>
    fun getContent(moduleId:String):LiveData<Resource<ModuleEntity>>
    fun setCourseBookmark(course:CourseEntity,state:Boolean)
    fun setReadModule(module:ModuleEntity)
}