package app.interactive.academy.viewmodel

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    @After
    fun tearDown(){}

    @Test
    fun getCourses(){
        val courseEntities=viewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5,courseEntities.size)
    }

}