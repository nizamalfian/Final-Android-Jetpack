package app.interactive.academy.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.data.source.vo.Resource
import app.interactive.academy.ui.bookmark.BookmarkViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private val academyRepository= mock(AcademyRepository::class.java)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @After
    fun tearDown(){}
    @Test
    fun testGetCourses(){
        val courses=MutableLiveData<Resource<PagedList<CourseEntity>>>().also{
            val pagedList:PagedList<CourseEntity> = mock(PagedList::class.java) as PagedList<CourseEntity>
            it.value=Resource.success(pagedList)
        }
        `when`(academyRepository.getBookmarkedCoursesAsPaged()).thenReturn(courses)
        val observer:Observer<Resource<PagedList<CourseEntity>>> = mock(Observer::class.java) as Observer<Resource<PagedList<CourseEntity>>>
        viewModel.getBookmarks().observeForever(observer)
        verify(academyRepository).getBookmarkedCoursesAsPaged()
    }

}