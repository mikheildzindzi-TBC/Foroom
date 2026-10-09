package com.example.foroom.utils

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher

object CustomViewActions {
    fun clickDescendantOfClass(className: String, position: Int): ViewAction = object : ViewAction {
        override fun getConstraints(): Matcher<View> = isDisplayed()

        override fun getDescription() = "click $className at position $position"

        override fun perform(uiController: UiController, view: View) {
            val items = mutableListOf<View>()
            collect(view, className, items)
            require(position in items.indices) {
                "No $className at $position, found ${items.size}"
            }
            items[position].performClick()
            uiController.loopMainThreadUntilIdle()
        }
    }

    private fun collect(view: View, className: String, out: MutableList<View>) {
        if (view.javaClass.simpleName == className) {
            out.add(view)
            return
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) collect(view.getChildAt(i), className, out)
        }
    }
}