package com.example.foroom.steps

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage
import com.example.foroom.utils.waitUntilDisplayed

class LoginSteps {
    private val loginPage = LoginPage()
    private val profilePage = ProfilePage()

    fun enterUsername(username: String) = apply {
        waitUntilDisplayed(loginPage.usernameField)
        onView(loginPage.usernameField).perform(replaceText(username))
    }

    fun enterPassword(password: String) = apply {
        waitUntilDisplayed(loginPage.passwordField)
        onView(loginPage.passwordField).perform(replaceText(password))
        closeSoftKeyboard()
    }

    fun tapLogIn() = apply {
        waitUntilDisplayed(loginPage.logInButton)
        onView(loginPage.logInButton).perform(click())
    }

    fun verifyLoginScreenDisplayed() = apply {
        waitUntilDisplayed(loginPage.logInButton)
        onView(loginPage.logInButton).check(matches(isDisplayed()))
        onView(loginPage.signUpButton).check(matches(isDisplayed()))
    }

    fun verifyHomeScreenDisplayed() = apply {
        waitUntilDisplayed(profilePage.navBar)
        onView(profilePage.navBar).check(matches(isDisplayed()))
    }

    fun login(username: String, password: String) = apply {
        verifyLoginScreenDisplayed()
        enterUsername(username)
        enterPassword(password)
        tapLogIn()
    }
}