package com.example.foroom.utils

import android.graphics.Rect
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.swiper
import org.hamcrest.Matcher

enum class SwipeDirection {
    /** Finger moves down: reveals older messages. */
    TO_OLDER,

    /** Finger moves up: reveals newer messages. */
    TO_NEWER
}

object ListScroller {

    private fun boundsOf(list: Matcher<View>): Rect {
        val bounds = Rect()
        onView(list).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> = isDisplayed()
            override fun getDescription() = "read on-screen bounds of the list"
            override fun perform(uiController: UiController, view: View) {
                val loc = IntArray(2)
                view.getLocationOnScreen(loc)
                bounds.set(loc[0], loc[1], loc[0] + view.width, loc[1] + view.height)
            }
        })
        return bounds
    }

    fun swipeList(list: Matcher<View>, direction: SwipeDirection) {
        val b = boundsOf(list)
        val top = b.top + (b.height() * 0.2).toInt()
        val bottom = b.top + (b.height() * 0.8).toInt()
        when (direction) {
            SwipeDirection.TO_OLDER -> swiper(top, bottom, 300)
            SwipeDirection.TO_NEWER -> swiper(bottom, top, 300)
        }
    }

    private fun isShownNow(target: Matcher<View>): Boolean = try {
        onView(target).check(matches(isDisplayed()))
        true
    } catch (e: NoMatchingViewException) {
        false
    }

    fun swipeUntilDisplayed(
        list: Matcher<View>,
        target: Matcher<View>,
        direction: SwipeDirection = SwipeDirection.TO_OLDER,
        maxSwipes: Int = 20
    ) {
        repeat(maxSwipes + 1) { attempt ->
            if (isShownNow(target)) return
            if (attempt < maxSwipes) swipeList(list, direction)
        }
        throw AssertionError("Target not found after $maxSwipes swipes ($direction): $target")
    }
}