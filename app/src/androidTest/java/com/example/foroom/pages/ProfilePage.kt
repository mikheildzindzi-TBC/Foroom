package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.utils.waitUntilDisplayed
import org.hamcrest.CoreMatchers.allOf

class ProfilePage {

    private val navBar = allOf(withId(R.id.navBar), isDisplayed())
    private val logOutRow = allOf(withId(R.id.signOutItem), isDisplayed())
    private val changePass = allOf(withId(R.id.changePasswordItem), isDisplayed())
    private val changeLang = allOf(withId(R.id.changeLanguageItem), isDisplayed())

    fun isLoggedIn(): Boolean = try {
        onView(navBar).check(matches(isDisplayed()))
        true
    } catch (e: Throwable) {
        false
    }

    fun assertHomeDisplayed() {
        waitUntilDisplayed(navBar)
        onView(navBar).check(matches(isDisplayed()))
    }

    fun assertLabelDisplayed(label: String) {
        val matcher = allOf(withText(label), isDisplayed())
        waitUntilDisplayed(matcher)
        onView(matcher).check(matches(isDisplayed()))
    }

    fun tapLogOut() {
        waitUntilDisplayed(logOutRow)
        onView(logOutRow).perform(click())
    }

    fun tapChangePass() {
        waitUntilDisplayed(changePass)
        onView(changePass).perform(click())
    }

    fun tapChangeLang() {
        waitUntilDisplayed(changeLang)
        onView(changeLang).perform(click())
    }
}