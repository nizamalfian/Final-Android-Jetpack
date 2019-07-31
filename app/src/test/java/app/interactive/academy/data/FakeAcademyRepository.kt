package app.interactive.academy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import app.interactive.academy.data.source.NetworkBoundResource
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.ApiResponse
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.data.source.remote.response.ContentResponse
import app.interactive.academy.data.source.remote.response.CourseResponse
import app.interactive.academy.data.source.remote.response.ModuleResponse
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.utils.AppExecutors

/**
 * Created by L
 *
 * on 7/8/2019
 */
class FakeAcademyRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appExecutors: AppExecutors) :
    AcademyDataSource {

    override fun getBookmarkedCoursesAsPaged(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object : NetworkBoundResource<PagedList<CourseEntity>,List<CourseResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<CourseEntity>> =
                LivePagedListBuilder(localRepository.getBookmarkedCoursesAsPaged(),20).build()

            override fun shouldFetch(data: PagedList<CourseEntity>): Boolean = false

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>>? = null

            override fun saveCallResult(data: List<CourseResponse>) {}

        }.asLiveData()
    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>,List<CourseResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<CourseEntity>> = localRepository.getAllCourses()

            override fun shouldFetch(data: List<CourseEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> = remoteRepository.getAllCoursesAsLiveData()

            override fun saveCallResult(data: List<CourseResponse>) {
                ArrayList<CourseEntity>().run {
                    data.forEach {
                        add(CourseEntity(
                            it.id,
                            it.title,
                            it.description,
                            it.date,
                            it.imagePath
                        ))
                    }
                    localRepository.insertCourses(this)
                }
            }
        }.asLiveData()
    }

    override fun getCourseWithModule(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule,List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<CourseWithModule> = localRepository.getCourseWithModules(courseId)

            override fun shouldFetch(data: CourseWithModule): Boolean = data.modules.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> = remoteRepository.getAllModulesByCourseAsLiveData(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                ArrayList<ModuleEntity>().run{
                    data.forEach{
                        add(
                            ModuleEntity(
                                it.moduleId,
                                courseId,
                                it.title,
                                it.position
                            )
                        )
                    }
                    localRepository.insertModules(this)
                }
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object:NetworkBoundResource<List<ModuleEntity>,List<ModuleResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<ModuleEntity>> = localRepository.getAllModulesByCourse(courseId)

            override fun shouldFetch(data: List<ModuleEntity>): Boolean = data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>>? = remoteRepository.getAllModulesByCourseAsLiveData(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                ArrayList<ModuleEntity>().run{
                    data.forEach{
                        add(ModuleEntity(
                            it.moduleId,
                            courseId,
                            it.title,
                            it.position
                        ))
                    }
                    localRepository.insertModules(this)
                }
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object:NetworkBoundResource<ModuleEntity,ContentResponse>(appExecutors){
            override fun loadFromDB(): LiveData<ModuleEntity> = localRepository.getModuleWithContent(moduleId)

            override fun shouldFetch(data: ModuleEntity): Boolean = data.contentEntity==null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>>? = remoteRepository.getContentAsLiveData(moduleId) as LiveData<ApiResponse<ContentResponse>>

            override fun saveCallResult(data: ContentResponse) {
                localRepository.updateContent(data.content,moduleId)
            }
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) {
        appExecutors.diskIO.execute{
            localRepository.setCourseBookmark(course,state)
        }
    }

    override fun setReadModule(module: ModuleEntity) {
        appExecutors.diskIO.execute{
            localRepository.setReadModule(module)
        }
    }

}