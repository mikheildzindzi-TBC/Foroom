package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

/** waits until the view matching is displayed. */
fun waitUntilDisplayed(matcher: Matcher<View>, timeoutMs: Long = 10_000, pollMs: Long = 250) {
    val end = System.currentTimeMillis() + timeoutMs
    var last: Throwable? = null
    while (System.currentTimeMillis() < end) {
        try {
            onView(matcher).check(matches(isDisplayed()))
            return
        } catch (e: Throwable) {
            last = e
            Thread.sleep(pollMs)
        }
    }
    throw last ?: AssertionError("View not displayed within $timeoutMs ms: $matcher")
}