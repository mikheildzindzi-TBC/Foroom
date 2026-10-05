package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.foroom.constants.Constants.LOGOUT_TEXT
import org.hamcrest.CoreMatchers.allOf
import com.example.design_system.R as DS

class ProfilePage {

    private val navBar = allOf(withId(R.id.navBar), isDisplayed())
    private val profileTab = allOf(withId(R.id.homeNavigationProfile), isDisplayed())
    private val logOutRow = allOf(withId(DS.id.listItemTextView), withText(LOGOUT_TEXT), isDisplayed())

    fun isLoggedIn(): Boolean = try {
        onView(navBar).check(matches(isDisplayed()))
        true
    } catch (e: Throwable) {
        false
    }

    fun openProfile() {
        onView(profileTab).perform(click())
    }

    fun tapLogOut() {
        waitUntilDisplayed(logOutRow)
        onView(logOutRow).perform(click())
    }
}