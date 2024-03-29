package com.nizamalfian.androidjetpack.ui.reader

import android.content.Intent
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.nizamalfian.androidjetpack.R
import com.nizamalfian.androidjetpack.ui.utils.RecyclerViewItemCountAssertion
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import com.nizamalfian.androidjetpack.utils.generateDummyCourses
import com.nizamalfian.androidjetpack.utils.EspressoIdlingResource
import org.junit.After

/**
 * Created by nizamalfian on 01/07/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class CourseReaderActivityTest {
    private val dummyCourse= generateDummyCourses()[0]
    @get:Rule
    val activityRule:ActivityTestRule<CourseReaderActivity> =
        object : ActivityTestRule<CourseReaderActivity>(CourseReaderActivity::class.java){
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext,CourseReaderActivity::class.java).also{
                    it.putExtra(CourseReaderActivity.EXTRA_COURSE_ID,dummyCourse.courseId)
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
    fun loadModules(){
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(RecyclerViewItemCountAssertion(6))
    }

    @Test
    fun clickModule(){
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }

}