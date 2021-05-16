package com.iswan.main.movflix.ui.main

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.iswan.main.movflix.R
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.manipulation.Ordering
import java.util.*


class MainActivityTest {

    private lateinit var strTvShows: String
    private lateinit var strMovie: String

    @Before
    fun init() {
        val resources = ApplicationProvider.getApplicationContext<Context>()
        strTvShows = resources.getString(R.string.tvshows)
        strMovie = resources.getString(R.string.movies)
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withText(R.string.movies)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                19  ,
                ViewActions.click()
            )
        )
        Thread.sleep(2000)
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strMovie)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_date), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_genres), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_duration), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_score), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_tagline), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_overview), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_status), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_language), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_budget), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_revenue), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_homepage), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.rv_companies), withTagValue(`is`(strMovie)))).scrollToMatchesNotEmpty()
    }

    @Test
    fun loadTvShows() {
        onView(withText(R.string.tvshows)).perform(ViewActions.click())
        Thread.sleep(2000)
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                19,
                ViewActions.click()
            )
        )
        Thread.sleep(2000)
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(strTvShows)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_genres), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_duration), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_score), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_tagline), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_overview), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(withId(R.id.rv_seasons)).scrollToMatches()
        onView(allOf(withId(R.id.tv_status), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_language), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.tv_homepage), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
        onView(allOf(withId(R.id.rv_companies), withTagValue(`is`(strTvShows)))).scrollToMatchesNotEmpty()
    }

}

private fun ViewInteraction.scrollToMatches() {
    perform(scrollTo())
    check(matches(isDisplayed()))
}

private fun ViewInteraction.scrollToMatchesNotEmpty() {
    perform(scrollTo())
    check(matches(isDisplayed()))
    check(matches(not(withText(""))))
}