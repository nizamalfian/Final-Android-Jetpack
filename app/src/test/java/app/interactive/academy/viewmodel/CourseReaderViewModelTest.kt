package app.interactive.academy.viewmodel

import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.source.local.entity.ContentEntity
import app.interactive.academy.ui.reader.CourseReaderViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertNotNull
import org.mockito.Mockito

/**
 * Created by nizamalfian on 30/06/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var content: ContentEntity
    private lateinit var moduleId:String
    private val academyRepository= Mockito.mock(AcademyRepository::class.java)

    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.courseId="a14"
        moduleId="a14m1"
        val title= viewModel.getModules()?.get(0)?.title
        moduleId="a14m1"
        content=
            ContentEntity("<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Test
    fun getModules() {
        val modules=viewModel.getModules()
        assertNotNull(modules)
        assertEquals(7,modules?.size)
    }

    @Test
    fun getSelectedModule() {
        viewModel.moduleId=moduleId
        val moduleEntity=viewModel.getSelectedModule()
        assertNotNull(moduleEntity)
        val contentEntity=moduleEntity?.contentEntity
        assertNotNull(contentEntity)
        val content=contentEntity?.content
        assertNotNull(content)
        assertEquals(content, this.content.content)
    }
}