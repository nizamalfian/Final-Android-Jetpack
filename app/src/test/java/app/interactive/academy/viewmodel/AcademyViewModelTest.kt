package app.interactive.academy.viewmodel

import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.ui.academy.AcademyViewModel
import app.interactive.academy.utils.generateDummyCourses
import app.interactive.academy.utils.generateDummyCoursesUnitTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull as assertNotNull1


/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel
    private val academyRepository= mock(AcademyRepository::class.java)

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
    fun testGetCourses(){
        `when`(academyRepository.getAllCourses()).thenReturn(generateDummyCoursesUnitTest())
        val courseEntities=viewModel.getCourses()
        verify(academyRepository).getAllCourses()
        assertNotNull1(courseEntities)
        assertEquals(5,courseEntities.size)
    }

}