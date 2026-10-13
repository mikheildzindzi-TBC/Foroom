package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class LoginPage {

    val usernameField: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.userNameInput)),
        isDisplayed()
    )

    val passwordField: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.passwordInput)),
        isDisplayed()
    )

    val logInButton: Matcher<View> = allOf(
        withId(R.id.logInButton),
        isDisplayed()
    )

    val signUpButton: Matcher<View> = allOf(
        withId(R.id.signUpButton),
        isDisplayed()
    )
}