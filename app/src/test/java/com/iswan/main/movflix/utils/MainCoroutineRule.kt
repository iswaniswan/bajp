package com.iswan.main.movflix.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {
    override fun starting(description: Description?) {
        super.starting(description)
        InstantTaskExecutorRule()
        System.out.println("MainCoroutineRule starting executed")
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
        System.out.println("MainCoroutineRule finished executed")
    }
}