package app.interactive.academy.viewmodel

import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.ui.bookmark.BookmarkViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private val academyRepository= Mockito.mock(AcademyRepository::class.java)

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
    fun testGetCourses(){
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(generateDummyCourses())
        val courseEntities=viewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourses()
        assertNotNull(courseEntities)
        assertEquals(5,courseEntities.size)
    }

}