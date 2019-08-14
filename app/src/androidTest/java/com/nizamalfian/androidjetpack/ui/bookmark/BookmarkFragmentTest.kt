package com.nizamalfian.androidjetpack.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import com.nizamalfian.androidjetpack.testing.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.nizamalfian.androidjetpack.R
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.nizamalfian.androidjetpack.utils.EspressoIdlingResource
import com.nizamalfian.androidjetpack.ui.utils.RecyclerViewItemCountAssertion
import com.nizamalfian.androidjetpack.ui.utils.RecyclerViewMinimumItemAssertion
import org.junit.After

/**
 * Created by nizamalfian on 01/07/2019.
 */
@RunWith(MockitoJUnitRunner::class)
class BookmarkFragmentTest {
    @get:Rule
    val activityRule= ActivityTestRule(SingleFragmentActivity::class.java)
    private lateinit var bookmarkFragment:BookmarkFragment

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
        bookmarkFragment=BookmarkFragment()
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun loadBookmarks(){
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewMinimumItemAssertion(0))
    }

}