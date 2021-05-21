package com.iswan.main.movflix.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {

    private const val RESOURCE = "APP"
    val idling = CountingIdlingResource(RESOURCE)

    fun increment() = idling.increment()

    fun decrement() = idling.decrement()

}