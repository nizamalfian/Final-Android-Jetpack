package app.interactive.academy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.data.dummy.generateDummyModules
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.local.entity.ModuleEntity
import app.interactive.academy.ui.reader.CourseReaderViewModel
import app.interactive.academy.utils.generateRemoteDummyModulesUnitTest
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private val academyRepository= mock(AcademyRepository::class.java)
    private val dummyCourse:CourseEntity= generateDummyCourses()[0]
    private val courseId:String=dummyCourse.courseId
    private val dummyModules= generateDummyModules(courseId)
    private val moduleId=dummyModules[0].moduleId
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository).also{
            it.courseId=courseId
            it.moduleId=moduleId
        }
    }

    @Test
    fun testGetModules(){
        val moduleEntities=MutableLiveData<ArrayList<ModuleEntity>>().also{
            it.value=dummyModules
        }
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)
        val observer = mock(Observer::class.java) as Observer<ArrayList<ModuleEntity>>
        viewModel.getModules().observeForever(observer)
        verify(academyRepository).getAllModulesByCourse(courseId)
    }

    @Test
    fun testGetSelectedModule(){
        val content="<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        val dummyModule = dummyModules[0].copy(contentEntity = ContentEntity(content))
        val moduleEntity=MutableLiveData<ModuleEntity>().also {
            it.value=dummyModule
        }
        `when`(academyRepository.getContent(courseId,moduleId)).thenReturn(moduleEntity)
//        viewModel.moduleId=moduleId
        val observer = mock(Observer::class.java) as Observer<ModuleEntity>
        viewModel.getSelectedModule().observeForever(observer)
        verify(academyRepository).getContent(courseId,moduleId)
    }

    /*@Test
    fun testGetModules(){
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)
        val modulesEntities=viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modulesEntities)
        assertEquals(7,modulesEntities?.size)
    }

    @Test
    fun getSelectedModule(){
        val content="<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        val moduleEntity:ModuleEntity=dummyModules[0].copy(contentEntity= ContentEntity(content))

        `when`(academyRepository.getContent(courseId,moduleId)).thenReturn(moduleEntity)
        val entity=viewModel.getSelectedModule()
        verify(academyRepository).getContent(courseId,moduleId)
        assertNotNull(entity)

        val contentEntity=entity?.contentEntity
        assertNotNull(contentEntity)

        val resultContent=contentEntity?.content
        assertNotNull(resultContent)
        assertEquals(content,resultContent)
    }*/
}