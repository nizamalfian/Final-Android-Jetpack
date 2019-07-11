package app.interactive.academy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.ui.academy.AcademyViewModel
import app.interactive.academy.utils.generateDummyCoursesUnitTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner




/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    private val academyRepository= mock(AcademyRepository::class.java)
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @After
    fun tearDown(){}

    /*@Test
    fun getCourses(){
        val courseEntities=viewModel.getCourses()
        assertNotNull1(courseEntities)
        assertEquals(5,courseEntities.size)
    }*/
    @Test
    fun testGetCourse(){
        val dummyCourses=MutableLiveData<ArrayList<CourseEntity>>().also {
            it.value=generateDummyCoursesUnitTest()
        }
        `when`(academyRepository.getAllCourses()).thenReturn(dummyCourses)
        val observer :Observer<ArrayList<CourseEntity>> = mock(Observer::class.java) as Observer<ArrayList<CourseEntity>>
//        val courseEntities=viewModel.getCourses()
        viewModel.getCourses().observeForever(observer)
        verify(academyRepository).getAllCourses()
        /*assertNotNull1(courseEntities)
        assertEquals(5,courseEntities.size)*/
    }

}