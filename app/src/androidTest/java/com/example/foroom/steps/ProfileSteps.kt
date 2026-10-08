package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.example.foroom.components.HomeBarNavigationComponent
import com.example.foroom.pages.ProfilePage
import com.example.foroom.utils.waitUntilDisplayed
import org.hamcrest.Matcher

class ProfileSteps {
    private val homeBar = HomeBarNavigationComponent()
    private val profilePage = ProfilePage()

    private fun tap(element: Matcher<View>) {
        waitUntilDisplayed(element)
        onView(element).perform(click())
    }

    private fun isLoggedIn(): Boolean = try {
        waitUntilDisplayed(profilePage.navBar, timeoutMs = 3_000)
        true
    } catch (e: Throwable) {
        false
    }

    fun openProfile() = apply { homeBar.openProfile() }

    fun logOut() = apply { tap(profilePage.logOutRow) }

    fun ensureLoggedOut() = apply {
        if (isLoggedIn()) {
            openProfile()
            logOut()
        }
    }
}