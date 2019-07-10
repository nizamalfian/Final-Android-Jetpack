package app.interactive.academy.viewmodel

import app.interactive.academy.data.AcademyRepository
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.data.dummy.generateDummyModules
import app.interactive.academy.data.source.local.entity.CourseEntity
import app.interactive.academy.ui.detail.DetailCourseViewModel

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
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val academyRepository = Mockito.mock(AcademyRepository::class.java)
    private val dummyCourse: CourseEntity = generateDummyCourses()[0]
    private val courseId: String = dummyCourse.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository).also {
            it.courseId = courseId
        }
        /*dummyCourse= CourseEntity(
            "a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg",
            false
        )*/
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetCourse() {
        `when`(academyRepository.getCourseWithModule(courseId)).thenReturn(dummyCourse)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModule(courseId)
        assertNotNull(courseEntity)
        val courseId = viewModel.courseId
        assertNotNull(courseId)
        assertEquals(courseId, dummyCourse.courseId)
    }

    @Test
    fun testGetModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(generateDummyModules(courseId))
        val modules = viewModel.getModules()
        verify(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(modules)
        assertEquals(7, modules.size)
    }
}