package com.iswan.main.movflix.ui.detail.tvshow

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.rule.ActivityTestRule
import com.iswan.main.movflix.R
import com.iswan.main.movflix.ui.main.MainActivity
import com.iswan.main.movflix.ui.main.MainActivityTest
import com.iswan.main.movflix.ui.main.scrollToMatches
import com.iswan.main.movflix.utils.IdlingResource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailTvShowActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(DetailTvShowActivity::class.java, true, false)

    private lateinit var tvShow: String

    private lateinit var mainActivityTest: MainActivityTest

    var updateFavourite: Boolean = false

    @Before
    fun init() {
        Intents.init()
        activityTestRule.launchActivity(Intent())

        shouldLaunchMainActivity()
        val resources = ApplicationProvider.getApplicationContext<Context>()
        tvShow = resources.getString(R.string.tvshows)
        IdlingRegistry.getInstance().register(IdlingResource.idling)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.idling)
        Intents.release()
        onFinish()
    }

    @Test
    fun loadTvShow() {
        onView(allOf(withId(R.id.iv_backdrop), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_title), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_genres), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_duration), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_score), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_tagline), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_overview), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(withId(R.id.rv_seasons)).scrollToMatches()
        onView(allOf(withId(R.id.tv_status), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_language), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.tv_homepage), withTagValue(`is`(tvShow)))).scrollToMatches()
        onView(allOf(withId(R.id.rv_companies), withTagValue(`is`(tvShow)))).scrollToMatches()
        if (updateFavourite) {
            onView(allOf(withId(R.id.action_favourite))).perform(click())
        }
    }

    private fun shouldLaunchMainActivity() {
        mainActivityTest = MainActivityTest()
        if (mainActivityTest.isActived) return
        try {
            val testRule = ActivityTestRule(MainActivity::class.java, true, false)
            testRule.launchActivity(Intent())
            mainActivityTest.loadTvShows()
        } catch (e: Exception) {
            println("Cannot start Activity -> ${e.message}")
        }
    }

    private fun onFinish() {
        if (this.activityTestRule.activity != null) {
            this.activityTestRule.finishActivity()
        }
    }
}