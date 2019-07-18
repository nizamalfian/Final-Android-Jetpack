package app.interactive.academy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse

/**
 * Created by L
 *
 * on 7/8/2019
 */
class FakeAcademyRepository(private val localRepository: LocalRepository, private val remoteRepository: RemoteRepository) :
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
    }

    override fun getAllModulesByCourse(courseId: String): MutableLiveData<ArrayList<ModuleEntity>> {
        return MutableLiveData<ArrayList<ModuleEntity>>().also{moduleResult->
            remoteRepository.getModules(courseId,object:RemoteRepository.LoadModulesCallback{
                override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                    ArrayList<ModuleEntity>().also{moduleList->
                        moduleResponses.forEach {
                            moduleList.add(
                                ModuleEntity(
                                    it.moduleId,
                                    it.courseId,
                                    it.title,
                                    it.position
                                )
                            )
                        }
                        moduleResult.postValue(moduleList)
                    }
                }

                override fun onDataNotAvailable() {}
            })
        }
    }

    override fun getBookmarkedCourses(): MutableLiveData<ArrayList<CourseEntity>> {
        return MutableLiveData<ArrayList<CourseEntity>>().also{courseResult->
            remoteRepository.getAllCourses(object:RemoteRepository.LoadCoursesCallback{
                override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                    ArrayList<CourseEntity>().also{courseList->
                        courseResponses.forEach{
                            courseList.add(
                                CourseEntity(
                                    it.id,
                                    it.title,
                                    it.description,
                                    it.date,
                                    it.imagePath
                                )
                            )
                        }
                        courseResult.postValue(courseList)
                    }
                }

                override fun onDataNotAvailable() {

                }
            })
        }
    }

    override fun getContent(courseId: String, moduleId: String): MutableLiveData<ModuleEntity> {
        return MutableLiveData<ModuleEntity>().also{moduleResult->
            remoteRepository.getModules(courseId,object:RemoteRepository.LoadModulesCallback{
                override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                    for(it in moduleResponses){
                        val id=it.moduleId
                        if(id==moduleId){
                            var module = ModuleEntity(id,it.courseId,it.title,it.position)
                            remoteRepository.getContent(id,object:RemoteRepository.GetContentCallback{
                                override fun onContentReceived(contentResponse: ContentResponse) {
                                    module=module.copy(contentEntity=ContentEntity(contentResponse.content))
                                    moduleResult.postValue(module)
                                }

                                override fun onDataNotAvailable() {}
                            })
                        }
                        break
                    }
                }

                override fun onDataNotAvailable() {}
            })
        }
    }
}