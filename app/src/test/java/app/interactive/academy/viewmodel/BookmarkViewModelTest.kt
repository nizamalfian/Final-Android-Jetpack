package app.interactive.academy.viewmodel

import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.ui.bookmark.BookmarkViewModel
import app.interactive.academy.utils.generateDummyCoursesUnitTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull
import org.mockito.Mockito.*

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private val academyRepository=mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @After
    fun tearDown(){}

    /*@Test
    fun getCourses(){
        val courseEntities=viewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5,courseEntities.size)
    }*/

    @Test
    fun testGetBookmarks(){
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(generateDummyCoursesUnitTest())
        val courses=viewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courses)
        assertEquals(courses.size,5)
    }

}