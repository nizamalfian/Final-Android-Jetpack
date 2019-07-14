package app.interactive.academy.ui.detail

import androidx.test.rule.ActivityTestRule
import app.interactive.academy.data.source.local.entity.CourseEntity

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import app.interactive.academy.R
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import app.interactive.academy.data.dummy.generateDummyCourses
import app.interactive.academy.utils.EspressoIdlingResource
import app.utils.RecyclerViewItemCountAssertion
import org.junit.After


/**
 * Created by nizamalfian on 01/07/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class DetailCourseActivityTest {
    private val dummyCourse: CourseEntity = generateDummyCourses()[0]

    @get:Rule
    val activityRule: ActivityTestRule<DetailCourseActivity> =
        object : ActivityTestRule<DetailCourseActivity>(DetailCourseActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, DetailCourseActivity::class.java).also {
                    it.putExtra(DetailCourseActivity.EXTRA_COURSE, dummyCourse.courseId)
                }
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun loadCourses(){
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyCourse.title)))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(withText(String.format("Deadline %s",dummyCourse.deadline))))
    }

    @Test
    fun loadModules(){
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(6))
    }
}