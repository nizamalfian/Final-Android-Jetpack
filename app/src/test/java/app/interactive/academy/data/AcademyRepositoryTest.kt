package app.interactive.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.interactive.academy.data.dummy.generateRemoteCourses
import app.interactive.academy.data.dummy.generateRemoteModules
import app.interactive.academy.data.dummy.getRemoteContent
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.remote.RemoteRepository
import app.interactive.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.eq
import junit.framework.Assert.assertEquals
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
    private val academyRepository=FakeAcademyRepository(local,remote)
    private val courseResponses= generateRemoteCourses()
    private val courseId=courseResponses[0].id
    private val moduleResponses= generateRemoteModules(courseId)
    private val moduleId=moduleResponses[0].moduleId
    private val content= getRemoteContent(moduleId)

    @Test
    fun testGetAllCourses(){
        doAnswer{invocation ->
            val callback=(invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
            callback.onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(anyOrNull())
        val courseEntities=LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remote,times(1)).getAllCourses(anyOrNull())
        assertEquals(courseResponses.size,courseEntities.size)
    }

    @Test
    fun testGetllAllModulesByCourse(){
        doAnswer{invocation ->
            val callback=(invocation.arguments[1] as RemoteRepository.LoadModulesCallback)
            callback.onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId),anyOrNull())
        val result=LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
        verify(remote,times(1)).getModules(eq(courseId),anyOrNull())
        assertEquals(moduleResponses.size,result.size)
    }

    @Test
    fun getBookmarkCourses(){
        doAnswer{invocation->
            val callback=(invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
            callback.onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(anyOrNull())
        val result=LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(remote,times(1)).getAllCourses(anyOrNull())
        assertEquals(courseResponses.size,result.size)
    }

    @Test
    fun testGetContent(){
        doAnswer{invocation->
            val callback=(invocation.arguments[1] as RemoteRepository.LoadModulesCallback)
            callback.onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), anyOrNull())
        doAnswer{invocation->
            val callback=(invocation.arguments[1] as RemoteRepository.GetContentCallback)
            callback.onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), anyOrNull())
        val contentResult=LiveDataTestUtil.getValue(academyRepository.getContent(courseId,moduleId))
        verify(remote,times(1)).getModules(eq(courseId), anyOrNull())
        verify(remote,times(1)).getContent(eq(moduleId), anyOrNull())
        assertEquals(content.content,contentResult.contentEntity?.content)
    }

    @Test
    fun testGetCourseWithModules(){
        doAnswer{invocation->
            val callback=(invocation.arguments[0] as RemoteRepository.LoadCoursesCallback)
            callback.onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(anyOrNull())
        val courseResults=LiveDataTestUtil.getValue(academyRepository.getCourseWithModule(eq(courseId)))
        verify(remote,times(1)).getAllCourses(anyOrNull())
        assertEquals(courseResponses[0].title,courseResults.title)
    }

    /*@Test
    fun testetAllModulesByCourse(){
        `when`(remote.getAllModules(courseId)).thenReturn(moduleResponses)
        val modules=academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getAllModules(courseId)
        assertNotNull(modules)
//        assertEquals(moduleResponses.size,modules.size)
    }

    @Test
    fun testGetBookmarkedCourses(){
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courses=academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courses)
//        assertEquals(courseResponses.size,courses.size)
    }

    @Test
    fun testGetContent(){
        `when`(remote.getAllModules(courseId)).thenReturn(moduleResponses)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule=academyRepository.getContent(courseId,moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
//        assertEquals(content.content,resultModule?.contentEntity?.content)
    }

    @Test
    fun testGetCourseWithModules(){
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val resultCourse=academyRepository.getCourseWithModule(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
//        assertEquals(courseResponses[0].title,resultCourse?.title)
    }*/
}