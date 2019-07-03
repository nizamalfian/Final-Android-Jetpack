package app.interactive.academy.viewmodel

import app.interactive.academy.data.CourseEntity

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
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private lateinit var dummyCourse:CourseEntity

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        dummyCourse= CourseEntity("a14",
            "Menjadi Android Developer Expert",
            "Dicoding sebagai satu-satunya Google Authorized Training Partner di Indonesia telah melalui proses penyusunan kurikulum secara komprehensif. Semua modul telah diverifikasi langsung oleh Google untuk memastikan bahwa materi yang diajarkan relevan dan sesuai dengan kebutuhan industri digital saat ini. Peserta akan belajar membangun aplikasi Android dengan materi Testing, Debugging, Application, Application UX, Fundamental Application Components, Persistent Data Storage, dan Enhanced System Integration.",
            "100 Hari",
            "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg",
            false)
    }

    @After
    fun tearDown(){}

    @Test
    fun getCourse() {
        viewModel.courseId=dummyCourse.courseId
        val course=viewModel.getCourse()
        assertNotNull(course)
        assertEquals(dummyCourse.courseId,course?.courseId)
        assertEquals(dummyCourse.deadline,course?.deadline)
        assertEquals(dummyCourse.description,course?.description)
        assertEquals(dummyCourse.imagePath,course?.imagePath)
        assertEquals(dummyCourse.title,course?.title)
    }

    @Test
    fun getModules() {
        val modules=viewModel.getModules()
        assertNotNull(modules)
        assertEquals(7,modules.size)
    }
}