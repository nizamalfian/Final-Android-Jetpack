package app.interactive.academy.ui.academy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import app.interactive.academy.testing.SingleFragmentActivity

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.matcher.ViewMatchers.withId
import app.interactive.academy.R
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import app.interactive.academy.utils.EspressoIdlingResource
import app.utils.RecyclerViewItemCountAssertion
import org.junit.After

/**
 * Created by nizamalfian on 01/07/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class AcademyFragmentTest {
    @get:Rule
    val activityRule=ActivityTestRule(SingleFragmentActivity::class.java)
    private lateinit var academyFragment:AcademyFragment

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
        academyFragment=AcademyFragment()
        activityRule.activity.setFragment(academyFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun loadCourses(){
        onView(withId(R.id.rv_academy)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy)).check(RecyclerViewItemCountAssertion(4))
    }

}