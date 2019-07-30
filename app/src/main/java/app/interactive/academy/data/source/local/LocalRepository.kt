package app.interactive.academy.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.local.room.AcademyDao

/**
 * Created by L
 *
 * on 7/8/2019
 */
class LocalRepository(private val academyDao: AcademyDao) {
    companion object{
        private var INSTANCE:LocalRepository?=null
        fun getInstance(academyDao: AcademyDao):LocalRepository{
            if(INSTANCE==null){
                INSTANCE=LocalRepository(academyDao)
            }
            return INSTANCE as LocalRepository
        }
    }

    fun getAllCourses():LiveData<List<CourseEntity>> = academyDao.getCourses()

    fun getBookmarkedCourses():LiveData<List<CourseEntity>> = academyDao.getBookmarkedCourses()

    fun getBookmarkedCoursesAsPaged():DataSource.Factory<Int,CourseEntity> = academyDao.getBookmarkedCoursesAsPaged()

    fun getCourseWithModules(courseId:String):LiveData<CourseWithModule> = academyDao.getCourseWithModulesById(courseId)

    fun getAllModulesByCourse(courseId:String):LiveData<List<ModuleEntity>> = academyDao.getModulesByCourseId(courseId)

    fun insertCourses(courses:List<CourseEntity>){
        academyDao.insertCourses(courses)
    }

    fun insertModules(modules:List<ModuleEntity>){
        academyDao.insertModules(modules)
    }

    fun setCourseBookmark(course:CourseEntity,newState:Boolean){
        course.isBookmarked=newState
        academyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId:String):LiveData<ModuleEntity> = academyDao.getModuleById(moduleId)

    fun updateContent(content:String,moduleId:String){
        academyDao.updateModuleByContent(content,moduleId)
    }

    fun setReadModule(module:ModuleEntity){
        module.isRead=true
        academyDao.updateModule(module)
    }
}