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
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import com.example.design_system.R as DS

class LoginPage {

    private fun input(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(allOf(withId(parentId), isDisplayed())))

    private val usernameField = input(R.id.userNameInput, DS.id.inputEditText)
    private val passwordField = input(R.id.passwordInput, DS.id.inputEditText)
    private val usernameDescription = input(R.id.userNameInput, DS.id.descriptionTextView)
    private val passwordDescription = input(R.id.passwordInput, DS.id.descriptionTextView)
    private val logInButton = allOf(withId(R.id.logInButton), isDisplayed())
    private val signUpButton = allOf(withId(R.id.signUpButton), isDisplayed())

    fun assertDisplayed() {
        waitUntilDisplayed(logInButton)
        onView(logInButton).check(matches(isDisplayed()))
        onView(signUpButton).check(matches(isDisplayed()))
    }

    fun typeUsername(value: String) {
        onView(usernameField).perform(replaceText(value))
    }

    fun typePassword(value: String) {
        onView(passwordField).perform(replaceText(value))
        closeSoftKeyboard()
    }

    fun tapLogIn() {
        onView(logInButton).perform(click())
    }

    fun tapSignUp() {
        onView(signUpButton).perform(click())
    }

    fun assertUsernameErrorShown() {
        waitUntilDisplayed(usernameDescription)
        onView(usernameDescription).check(matches(not(withText(""))))
    }

    fun assertPasswordErrorShown() {
        waitUntilDisplayed(passwordDescription)
        onView(passwordDescription).check(matches(not(withText(""))))
    }
}