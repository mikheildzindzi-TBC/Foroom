package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class ProfilePage {

    val navBar: Matcher<View> = allOf(
        withId(R.id.navBar),
        isDisplayed()
    )

    val logOutRow: Matcher<View> = allOf(
        withId(R.id.signOutItem),
        isDisplayed()
    )

    val changePass: Matcher<View> = allOf(
        withId(R.id.changePasswordItem),
        isDisplayed()
    )

    val changeLang: Matcher<View> = allOf(
        withId(R.id.changeLanguageItem),
        isDisplayed()
    )

    fun label(text: String): Matcher<View> = allOf(
        withText(text),
        isDisplayed()
    )
}