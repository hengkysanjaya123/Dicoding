package com.example.itss_wsc.myapplication

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LastMatchTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)


//    @Test
//    fun fragment_can_be_instantiated() {
//        activityRule.activity.runOnUiThread { val lastfragment = startlastfragment()
//
//        }
//        // Then use Espresso to test the Fragment
//        onView(withId(R.id.prevmatch)).check(matches(isDisplayed()))
//    }
//
//    private fun startlastfragment(): LastMatchFragment {
//        val activity = activityRule.activity as MainActivity
//        val transaction = activity.getSupportFragmentManager().beginTransaction()
//        val lastfragment = LastMatchFragment()
//        transaction.add(lastfragment, "lastFragment")
//        transaction.commit()
//
//        return lastfragment
//    }

    @Test
    fun testApp() {
        onView(withId(R.id.lastmatchfragment)).check(
            matches(isDisplayed())
        )
        onView(withId(R.id.recyclerResult))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerResult)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.recyclerResult)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))


        onView(withId(R.id.add_to_favorite)).perform(click())

        pressBack()

        onView(withId(R.id.favorites)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())
    }
}
