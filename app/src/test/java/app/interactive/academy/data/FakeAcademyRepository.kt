package app.interactive.academy.data

import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.RemoteRepository

/**
 * Created by L
 *
 * on 7/8/2019
 */
class FakeAcademyRepository(private val localRepository: LocalRepository, private val remoteRepository: RemoteRepository) :
    AcademyDataSource {

    companion object {
        private var INSTANCE: FakeAcademyRepository?=null

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepository): FakeAcademyRepository {
            if (INSTANCE == null) {
                synchronized(FakeAcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            FakeAcademyRepository(localRepository, remoteRepository)
                    }
                }
            }
            return INSTANCE as FakeAcademyRepository
        }
    }

    override fun getAllCourses(): List<CourseEntity> {
        return ArrayList<CourseEntity>().also {
            remoteRepository.getAllCourses().forEach { course ->
                it.add(
                    CourseEntity(
                        course.id,
                        course.title,
                        course.description,
                        course.date,
                        course.imagePath
                    )
                )
            }
        }
    }

    override fun getCourseWithModule(courseId: String): CourseEntity? {
        var course: CourseEntity? = null
        remoteRepository.getAllCourses().forEach {
            if (it.id == courseId) {
                course = CourseEntity(
                    it.id,
                    it.title,
                    it.description,
                    it.date,
                    it.imagePath
                )
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        return ArrayList<ModuleEntity>().also {
            remoteRepository.getAllModules(courseId).forEach { module ->
                it.add(
                    ModuleEntity(
                        module.moduleId,
                        module.courseId,
                        module.title,
                        module.position
                    )
                )
            }
        }
    }

    override fun getBookmarkedCourses(): ArrayList<CourseEntity> {
        return ArrayList<CourseEntity>().also {
            remoteRepository.getAllCourses().forEach { course ->
                it.add(
                    CourseEntity(
                        course.id,
                        course.title,
                        course.description,
                        course.date,
                        course.imagePath
                    )
                )
            }
        }
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity? {
        var moduleEntity: ModuleEntity? = null
        remoteRepository.getAllModules(courseId).forEach {
            if (it.moduleId == moduleId) {
                moduleEntity = ModuleEntity(
                    it.moduleId,
                    it.courseId,
                    it.title,
                    it.position,
                    remoteRepository.getContent(moduleId)?.content?.let { content ->
                        ContentEntity(
                            content
                        )
                    }
                )
            }
        }
        return moduleEntity
    }
}