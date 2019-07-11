package app.interactive.academy.data

import androidx.lifecycle.MutableLiveData
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

    override fun getAllCourses(): MutableLiveData<ArrayList<CourseEntity>> {
        return MutableLiveData<ArrayList<CourseEntity>>().also{
            it.postValue(ArrayList<CourseEntity>().also {
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
            })
        }
    }

    override fun getCourseWithModule(courseId: String): MutableLiveData<CourseEntity> {
        return MutableLiveData<CourseEntity>().also{
            var course: CourseEntity? = null
            remoteRepository.getAllCourses().forEach {courseResult->
                if (courseResult.id == courseId) {
                    course = CourseEntity(
                        courseResult.id,
                        courseResult.title,
                        courseResult.description,
                        courseResult.date,
                        courseResult.imagePath
                    )
                }
            }
            it.postValue(course)
        }
    }

    override fun getAllModulesByCourse(courseId: String): MutableLiveData<ArrayList<ModuleEntity>> {
        return MutableLiveData<ArrayList<ModuleEntity>>().also{
            it.postValue(ArrayList<ModuleEntity>().also {
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
            })
        }
    }

    override fun getBookmarkedCourses(): MutableLiveData<ArrayList<CourseEntity>> {
        return MutableLiveData<ArrayList<CourseEntity>>().also{
            it.postValue(ArrayList<CourseEntity>().also {
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
            })
        }

    }

    override fun getContent(courseId: String, moduleId: String): MutableLiveData<ModuleEntity> {
        return MutableLiveData<ModuleEntity>().also{
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
            it.postValue(moduleEntity)
        }
    }
}