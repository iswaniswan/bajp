package com.iswan.main.movflix.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.iswan.main.movflix.R
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

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
        onView(withId(R.id.tv_budget)).check(matches(isDisplayed())).also {
            check(withId(R.id.tv_budget).toString().isNotBlank())
        }
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
        onView(withId(R.id.rv_seasons)).check(matches(isDisplayed()))
    }

}