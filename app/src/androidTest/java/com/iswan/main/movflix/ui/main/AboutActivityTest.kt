package com.iswan.main.movflix.ui.main

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.iswan.main.movflix.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AboutActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(AboutActivity::class.java)

    private lateinit var about: String

    @Before
    fun init() {
        val resources = ApplicationProvider.getApplicationContext<Context>()
        about = resources.getString(R.string.about)
    }

    @Test
    fun pageLoaded() {
        onView(allOf(withId(R.id.logo), withTagValue(`is`(about)))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.text_app_title), withTagValue(`is`(about)))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.text_app_version), withTagValue(`is`(about)))).check(matches(isDisplayed()))
    }

}