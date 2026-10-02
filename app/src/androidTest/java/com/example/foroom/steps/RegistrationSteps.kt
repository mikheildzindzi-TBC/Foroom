package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.foroom.pages.RegistrationPage
import com.example.foroom.pages.waitUntilDisplayed
import org.hamcrest.CoreMatchers.allOf

class RegistrationSteps(private val page: RegistrationPage = RegistrationPage()) {

    fun verifyRegistrationScreenDisplayed() = apply { page.assertDisplayed() }

    fun register(username: String, password: String, avatarPosition: Int = 0) = apply {
        page.typeUsername(username)
        page.typePassword(password)
        page.typeRepeatPassword(password)
        page.selectAvatar(avatarPosition)
        page.tapSignUp()
    }

    fun verifyHomeScreenDisplayed() = apply {
        val navBar = allOf(withId(R.id.navBar), isDisplayed())
        waitUntilDisplayed(navBar, timeoutMs = 15_000)
        onView(navBar).check(matches(isDisplayed()))
    }
}