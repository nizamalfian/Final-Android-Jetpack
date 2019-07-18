package app.interactive.academy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.data.source.remote.response.CourseResponse

/**
 * Created by L
 *
 * on 7/8/2019
 */
class AcademyRepository(private val localRepository: LocalRepository, private val remoteRepository: RemoteRepository) :
    AcademyDataSource {
    val courseResults = MutableLiveData<ArrayList<CourseEntity>>()

    companion object {
        private var INSTANCE: AcademyRepository?=null

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepository): AcademyRepository {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            AcademyRepository(localRepository, remoteRepository)
                    }
                }
            }
            return INSTANCE as AcademyRepository
        }
    }

    override fun getAllCourses(): LiveData<ArrayList<CourseEntity>> {
        return MutableLiveData<ArrayList<CourseEntity>>().also{
            remoteRepository.getAllCourses(object:RemoteRepository.LoadCoursesCallback{
                override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                    val courseList=ArrayList<CourseEntity>().also{courseLists->
                        courseResponses.forEach {
                            courseLists.add(CourseEntity(
                                it.id,
                                it.title,
                                it.description,
                                it.date,
                                it.imagePath
                            ))
                        }
                    }
                    courseResults.postValue(courseList)
                }

                override fun onDataNotAvailable() {

                }
            })

            return courseResults
        }
        /*return MutableLiveData<ArrayList<CourseEntity>>().also{
            it.postValue(
                ArrayList<CourseEntity>().also {
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
            )
        }*/
    }

    override fun getCourseWithModule(courseId: String): MutableLiveData<CourseEntity> {
        return MutableLiveData<CourseEntity>().also {result->
            remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback{
                override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                    courseResponses.forEach {
                        if(it.id==courseId){
                            result.postValue(CourseEntity(
                                it.id,
                                it.title,
                                it.description,
                                it.date,
                                it.imagePath
                            ))
                        }
                    }
                }

                override fun onDataNotAvailable() {

                }
            })
        }
        /*return MutableLiveData<CourseEntity>().also{result->
            remoteRepository.getAllCourses().forEach {
                if (it.id == courseId) {
                    result.postValue(CourseEntity(
                        it.id,
                        it.title,
                        it.description,
                        it.date,
                        it.imagePath
                    ))
                }
            }
        }*/
    }

    override fun getAllModulesByCourse(courseId: String): MutableLiveData<ArrayList<ModuleEntity>> {
        return MutableLiveData<ArrayList<ModuleEntity>>().also{
            it.postValue(
                ArrayList<ModuleEntity>().also {
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
        /*return MutableLiveData<ArrayList<CourseEntity>>().also{

        }*/
        return MutableLiveData<ArrayList<CourseEntity>>().also {
            it.postValue(
                ArrayList<CourseEntity>().also {
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
        return MutableLiveData<ModuleEntity>().also{result->
            remoteRepository.getAllModules(courseId).forEach {
                if (it.moduleId == moduleId) {
                    result.postValue(ModuleEntity(
                        it.moduleId,
                        it.courseId,
                        it.title,
                        it.position,
                        remoteRepository.getContent(moduleId)?.content?.let { content ->
                            ContentEntity(
                                content
                            )
                        }
                    ))
                }
            }
        }
    }
}