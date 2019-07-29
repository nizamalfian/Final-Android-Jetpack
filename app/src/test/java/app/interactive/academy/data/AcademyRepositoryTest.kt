package app.interactive.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import app.interactive.academy.data.dummy.*
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.utils.InstantAppExecutors
import app.interactive.academy.utils.LiveDataTestUtil
import app.interactive.academy.utils.generateDummyModulesWithContent
import app.interactive.academy.utils.getGenerateDummyCourseWithModules
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.eq
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Created by L
 *
 *
 * on 7/9/2019
 */
class AcademyRepositoryTest{
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val local = mock(LocalRepository::class.java)
    private val remote = mock(RemoteRepository::class.java)
    private val instantAppExecutors:InstantAppExecutors=mock(InstantAppExecutors::class.java)
    private val academyRepository=FakeAcademyRepository(local,remote,instantAppExecutors)
    private val courseResponses= generateRemoteCourses()
    private val courseId=courseResponses[0].id
    private val moduleResponses= generateRemoteModules(courseId)
    private val moduleId=moduleResponses[0].moduleId
    private val content= getRemoteContent(moduleId)

    @Test
    fun testGetAllCourses(){
        val dummyCourses = MutableLiveData<List<CourseEntity>>().also{
            it.value= generateDummyCourses()
        }
        `when`(local.getAllCourses()).thenReturn(dummyCourses)
        val result :Resource<List<CourseEntity>> = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(local).getAllCourses()
        assertNotNull(result.data)
    }

    @Test
    fun testGetAllModuleByCourse(){
        val dummyModules = MutableLiveData<List<ModuleEntity>>().also{
            it.value= generateDummyModules(courseId)
        }
        `when`(local.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val result:Resource<List<ModuleEntity>> = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
        verify(local).getAllModulesByCourse(courseId)
        assertNotNull(result)
    }

    @Test
    fun testGetBookmarkedCourses(){
        val dummyCourse = MutableLiveData<List<CourseEntity>>().also{
            it.value= generateDummyCourses()
        }
        `when`(local.getBookmarkedCourses()).thenReturn(dummyCourse)
        val result:Resource<List<CourseEntity>> = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(local).getBookmarkedCourses()
        assertNotNull(result)
    }

    @Test
    fun getContent(){
        val dummyEntity = MutableLiveData<ModuleEntity>().also{
            it.value= generateDummyModulesWithContent(courseId)
        }
        `when`(local.getModuleWithContent(courseId)).thenReturn(dummyEntity)
        val result : Resource<ModuleEntity> = LiveDataTestUtil.getValue(academyRepository.getContent(courseId))
        verify(local).getModuleWithContent(courseId)
        assertNotNull(result)
    }

    @Test
    fun getCourseWithModules(){
        val dummyEntity = MutableLiveData<CourseWithModule>().also{
            it.value= getGenerateDummyCourseWithModules(generateDummyCourses()[0],true)
        }
        `when`(local.getCourseWithModules(courseId)).thenReturn(dummyEntity)
        val result : Resource<CourseWithModule> = LiveDataTestUtil.getValue(academyRepository.getCourseWithModule(courseId))
        verify(local).getCourseWithModules(courseId)
        assertNotNull(result)
    }
}