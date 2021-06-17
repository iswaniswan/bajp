package com.iswan.main.movflix.ui.main

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.iswan.main.movflix.R
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivityTest
import com.iswan.main.movflix.ui.detail.tvshow.DetailTvShowActivityTest
import com.iswan.main.movflix.utils.IdlingResource
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    private lateinit var strTvShows: String
    private lateinit var strMovie: String

    var isActived: Boolean = false

    @Before
    fun init() {
        Intents.init()
        activityTestRule.launchActivity(Intent())
        isActived = true
        val resources = ApplicationProvider.getApplicationContext<Context>()
        strTvShows = resources.getString(R.string.tvshows)
        strMovie = resources.getString(R.string.movies)
        IdlingRegistry.getInstance().register(IdlingResource.idling)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idling)
        isActived = false
        Intents.release()
        onFinish()
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.movies)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_movies)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                29
            )
        )
        onView(withId(R.id.rv_movies)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                29,
                click()
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.tvshows)).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_tvshow)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                29
            )
        )
        onView(withId(R.id.rv_tvshow)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                29,
                click()
            )
        )
    }

    @Test
    fun searchMovies() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.movies)).perform(click())
        val shawshank = "The Shawshank Redemption"
        onView(withId(R.id.action_search)).perform(click())
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.action_search))))
            .perform(typeText(shawshank))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        try {
            onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        } catch (e: AssertionFailedError) {
            println("rendering too fast")
            waitUntilLoaded { activityTestRule.activity.findViewById(R.id.rv_movies) }
        } finally {
            onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        }

        onView(withId(R.id.rv_movies)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strMovie))))
            .check(matches(withText(shawshank)))
    }

    @Test
    fun searchTvShows() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.tvshows)).perform(click())
        val mentalist = "The Mentalist"
        onView(withId(R.id.action_search)).perform(click())
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.action_search))))
            .perform(typeText(mentalist))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        try {
            onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        } catch (e: AssertionFailedError) {
            println("rendering too fast")
            waitUntilLoaded { activityTestRule.activity.findViewById(R.id.rv_tvshow) }
        } finally {
            onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        }

        onView(withId(R.id.rv_tvshow)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strTvShows))))
            .check(matches(withText(mentalist)))
    }

    private fun onFinish() {
        if (this.activityTestRule.activity != null) {
            this.activityTestRule.finishActivity()
        }
    }

    private inline fun waitUntilLoaded(crossinline recyclerProvider: () -> RecyclerView) {
        Espresso.onIdle()
        lateinit var recycler: RecyclerView
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            recycler = recyclerProvider()
        }
        while (recycler.hasPendingAdapterUpdates()) {
            Thread.sleep(500)
        }
    }
}

fun ViewInteraction.scrollToMatches() {
    perform(scrollTo())
    check(matches(isDisplayed()))
}