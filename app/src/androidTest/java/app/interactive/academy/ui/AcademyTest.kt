package app.interactive.academy.ui

import androidx.test.rule.ActivityTestRule
import app.interactive.academy.ui.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import app.interactive.academy.R
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition

/**
 * Created by nizamalfian on 01/07/2019.
 */
class AcademyTest {
    @get:Rule
    val activityRule=ActivityTestRule(HomeActivity::class.java)

    @Test
    fun toDetailActivityTest(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(actionOnItemAtPosition<ViewHolder>(0,click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
    }

    @Test
    fun toReaderActivityTest(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).perform(actionOnItemAtPosition<ViewHolder>(0,click()))

        onView(withId(R.id.btn_start)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_start)).perform(click())

        onView(withId(R.id.frame_container)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_module)).perform(actionOnItemAtPosition<ViewHolder>(0,click()))

        onView(withId(R.id.web_view)).check(matches(isDisplayed()))
    }
}