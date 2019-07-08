package app.interactive.academy.viewmodel

import app.interactive.academy.ui.academy.AcademyViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull as assertNotNull1

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @After
    fun tearDown(){}

    @Test
    fun getCourses(){
        val courseEntities=viewModel.getCourses()
        assertNotNull1(courseEntities)
        assertEquals(5,courseEntities.size)
    }

}