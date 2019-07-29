package app.interactive.academy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.CourseWithModule
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.ui.detail.DetailCourseViewModel
import app.interactive.academy.utils.generateRemoteDummyModulesUnitTest
import app.interactive.academy.utils.getGenerateDummyCourseWithModules

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val academyRepository = mock(AcademyRepository::class.java)
    private val dummyCourse: CourseEntity = generateDummyCourses()[0]
    private val courseId: String = dummyCourse.courseId
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository).also {
            it.setCourseId(courseId)
            it.setBookmark()
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetCourseWithModule(){
        val courseEntities = MutableLiveData<Resource<CourseWithModule>>().also{
            it.value=Resource.success(getGenerateDummyCourseWithModules(dummyCourse,true))
        }
        `when`(academyRepository.getCourseWithModule(courseId)).thenReturn(courseEntities)
        val observer = mock(Observer::class.java) as Observer<Resource<CourseWithModule>>
        viewModel.courseModule.observeForever(observer)
        verify(academyRepository).getCourseWithModule(courseId)

    }

    /*@Test
    fun testGetCourse(){
        val courseEntities=MutableLiveData<CourseEntity>().also{
            it.value=dummyCourse
        }
        `when`(academyRepository.getCourseWithModule(courseId)).thenReturn(courseEntities)
        val observer = mock(Observer::class.java) as Observer<CourseEntity>
        viewModel.getCourse().observeForever(observer)
        verify(academyRepository).getCourseWithModule(courseId)
    }

    @Test
    fun testGetModules(){
        val moduleEntities=MutableLiveData<ArrayList<ModuleEntity>>().also{
            it.value= generateRemoteDummyModulesUnitTest(courseId)
        }
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)
        val observer = mock(Observer::class.java) as Observer<ArrayList<ModuleEntity>>
        viewModel.getModules().observeForever(observer)
        verify(academyRepository).getAllModulesByCourse(courseId)
    }*/
}