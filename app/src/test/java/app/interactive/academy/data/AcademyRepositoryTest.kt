package app.interactive.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.interactive.academy.data.dummy.generateRemoteCourses
import app.interactive.academy.data.dummy.generateRemoteModules
import app.interactive.academy.data.dummy.getRemoteContent
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.remote.RemoteRepository
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
    private val academyRepository=FakeAcademyRepository(local,remote)
    private val courseResponses= generateRemoteCourses()
    private val courseId=courseResponses[0].id
    private val moduleResponses= generateRemoteModules(courseId)
    private val moduleId=moduleResponses[0].moduleId
    private val content= getRemoteContent(moduleId)

    @Test
    fun testGetAllCourses(){
        `when`(remote.getAllCourses()).thenReturn(courseResponses)
        val courseEntities=academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
//        assertEquals(courseResponses.size,courseEntities.size)
    }

    @Test
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
    }
}