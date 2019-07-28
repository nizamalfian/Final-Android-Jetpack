package app.interactive.academy.data.source.local.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.*
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity

/**
 * Created by nizamalfian on 25/07/2019.
 */
@Dao
interface AcademyDao {
    @WorkerThread
    @Query("SELECT * FROM CourseEntity")
    fun getCourses():LiveData<List<CourseEntity>>

    @WorkerThread
    @Query("SELECT * FROM CourseEntity where isBookmarked = 1")
    fun getBookmarkedCourses():LiveData<List<CourseEntity>>

    @Transaction
    @Query("SELECT * FROM CourseEntity WHERE courseId= :courseId")
    fun getCourseWithModulesById(courseId:String):LiveData<CourseWithModule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourses(courses:List<CourseEntity>):LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateCourse(course:CourseEntity):Int

    @Query("SELECT * FROM ModuleEntity WHERE courseId = :courseId")
    fun getModulesByCourseId(courseId:String):LiveData<List<ModuleEntity>>

    @Query("SELECT * FROM ModuleEntity WHERE moduleId = :moduleId")
    fun getModuleById(moduleId:String):LiveData<ModuleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertModules(modules:List<ModuleEntity>):LongArray

    @Update
    fun updateModule(module:ModuleEntity):Int

    @Query("UPDATE ModuleEntity SET content = :content WHERE moduleId = :moduleId")
    fun updateModuleByContent(content:String,moduleId:String):Int

}