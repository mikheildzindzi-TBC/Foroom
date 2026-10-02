package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Matcher
import com.example.design_system.R as DS

class RegistrationPage {

    private fun input(parentId: Int): Matcher<View> =
        allOf(withId(DS.id.inputEditText), isDescendantOfA(allOf(withId(parentId), isDisplayed())))

    private val usernameField = input(R.id.userNameInput)
    private val passwordField = input(R.id.passwordInput)
    private val repeatPasswordField = input(R.id.repeatPasswordInput)
    private val avatarList = allOf(withId(R.id.listView), isDisplayed())
    private val signUpButton = allOf(withId(R.id.signUpButton), isDisplayed())

    fun assertDisplayed() {
        waitUntilDisplayed(allOf(withId(R.id.repeatPasswordInput), isDisplayed()))
        onView(avatarList).check(matches(isDisplayed()))
    }

    fun typeUsername(value: String) {
        onView(usernameField).perform(replaceText(value))
    }

    fun typePassword(value: String) {
        onView(passwordField).perform(replaceText(value))
    }

    fun typeRepeatPassword(value: String) {
        onView(repeatPasswordField).perform(replaceText(value))
        closeSoftKeyboard()
    }

    /** Direct child of the avatar container at [position] (listView is a custom ViewGroup, not an AdapterView). */
    private fun avatarAt(position: Int): Matcher<View> = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("avatar at position $position inside listView")
        }

        override fun matchesSafely(view: View): Boolean {
            val parent = view.parent as? ViewGroup ?: return false
            return avatarList.matches(parent) &&
                    parent.childCount > position &&
                    parent.getChildAt(position) === view
        }
    }

    fun selectAvatar(position: Int = 0) {
        waitUntilDisplayed(avatarAt(position))
        onView(avatarAt(position)).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }
}