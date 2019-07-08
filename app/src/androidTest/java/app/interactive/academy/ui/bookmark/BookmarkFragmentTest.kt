package app.interactive.academy.ui.bookmark

import androidx.test.espresso.Espresso.onView
import androidx.test.rule.ActivityTestRule
import app.interactive.academy.testing.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import androidx.test.espresso.matcher.ViewMatchers.withId
import app.interactive.academy.R
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import app.utils.RecyclerViewItemCountAssertion

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
        bookmarkFragment=BookmarkFragment()
        activityRule.activity.setFragment(bookmarkFragment)
    }

    @Test
    fun loadBookmarks(){
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).check(RecyclerViewItemCountAssertion(5))
    }

}