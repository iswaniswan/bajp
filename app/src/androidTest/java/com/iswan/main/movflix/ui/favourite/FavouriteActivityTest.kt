package com.iswan.main.movflix.ui.favourite

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.iswan.main.movflix.R
import com.iswan.main.movflix.ui.detail.movie.DetailMovieActivityTest
import com.iswan.main.movflix.ui.detail.tvshow.DetailTvShowActivityTest
import com.iswan.main.movflix.utils.IdlingResource
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavouriteActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(FavouriteActivity::class.java)

    private lateinit var strTvShows: String
    private lateinit var strMovie: String

    private lateinit var detailMovieActivity: DetailMovieActivityTest
    private lateinit var detailTvShowActivity: DetailTvShowActivityTest

    @Before
    fun init() {
        val resources = ApplicationProvider.getApplicationContext<Context>()
        strTvShows = resources.getString(R.string.tvshows)
        strMovie = resources.getString(R.string.movies)
        IdlingRegistry.getInstance().register(IdlingResource.idling)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idling)
    }

    @Test
    fun favouritesMovie() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.movies)).perform(click())
        if (!hasItem(R.id.rv_movies)) {
            addMovieToFavourites()
        }
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.action_favourite))).perform(click())
    }

    @Test
    fun favouritesTvShow() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.tvshows)).perform(click())
        if (!hasItem(R.id.rv_tvshow)) {
            addTvShowToFavourites()
        }
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(allOf(withId(R.id.action_favourite))).perform(click())
    }

    private fun hasItem(id: Int): Boolean {
        return try {
            onView(withId(id)).check(matches(isDisplayed()))
            true
        } catch (e: AssertionFailedError) {
            false
        }
    }

    private fun addMovieToFavourites() {
        detailMovieActivity = DetailMovieActivityTest()
        try {
            detailMovieActivity.init()
            detailMovieActivity.updateFavourite = true
            detailMovieActivity.loadMovie()
            detailMovieActivity.tearDown()
        } catch (e: Exception) {
            println("Cannot start Activity -> ${e.message}")
        }
    }

    private fun addTvShowToFavourites() {
        detailTvShowActivity = DetailTvShowActivityTest()
        try {
            detailTvShowActivity.init()
            detailTvShowActivity.updateFavourite = true
            detailTvShowActivity.loadTvShow()
            detailTvShowActivity.tearDown()
        } catch (e: Exception) {
            println("Cannot start Activity -> ${e.message}")
        }
    }

}
