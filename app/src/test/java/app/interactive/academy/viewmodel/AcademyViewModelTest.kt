package app.interactive.academy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.vo.Resource
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
    private val USERNAME="Dicoding"
    private lateinit var viewModel: AcademyViewModel
    private val academyRepository= mock(AcademyRepository::class.java)
    @get:Rule val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @After
    fun tearDown(){}

    @Test
    fun testGetCourse(){
        val dummyCourses=MutableLiveData<Resource<List<CourseEntity>>>().also {
            it.value=Resource.success(generateDummyCoursesUnitTest())
        }
        `when`(academyRepository.getAllCourses()).thenReturn(dummyCourses)
        val observer :Observer<Resource<List<CourseEntity>>> = mock(Observer::class.java) as Observer<Resource<List<CourseEntity>>>
        viewModel.setUsername(USERNAME)
        viewModel.getCourses().observeForever(observer)
        verify(academyRepository).getAllCourses()
    }

}