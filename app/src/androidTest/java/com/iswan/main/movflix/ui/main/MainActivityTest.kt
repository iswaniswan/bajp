package com.iswan.main.movflix.ui.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.iswan.main.movflix.R
import com.iswan.main.movflix.utils.IdlingResource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var strTvShows: String
    private lateinit var strMovie: String

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
    fun loadMovies() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.movies)).perform(ViewActions.click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_movies)).perform(swipeDown())
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0  ,
                ViewActions.click()
            )
        )
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_date), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_genres), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_duration), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_score), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_tagline), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_overview), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_status), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_language), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_budget), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_revenue), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_homepage), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.rv_companies), withTagValue(`is`(strMovie)))).scrollToMatches()
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.tvshows)).perform(ViewActions.click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_tvshow)).perform(swipeDown())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_genres), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_duration), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_score), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_tagline), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_overview), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(withId(R.id.rv_seasons)).scrollToMatches()
        onView(allOf(withId(R.id.tv_status), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_language), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_homepage), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.rv_companies), withTagValue(`is`(strTvShows)))).scrollToMatches()
    }
}

private fun ViewInteraction.scrollToMatches() {
    perform(scrollTo())
    check(matches(isDisplayed()))
}