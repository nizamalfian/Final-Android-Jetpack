package app.interactive.academy.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.interactive.academy.data.dummy.generateRemoteCourses
import app.interactive.academy.data.dummy.generateRemoteModules
import app.interactive.academy.data.dummy.getRemoteContent
import app.interactive.academy.data.source.local.LocalRepository
import app.interactive.academy.data.source.remote.RemoteRepository
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

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
//        `when`()
    }
}