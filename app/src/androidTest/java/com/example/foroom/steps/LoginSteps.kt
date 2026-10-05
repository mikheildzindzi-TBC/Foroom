package com.example.foroom.steps

import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage

class LoginSteps(
    private val loginPage: LoginPage = LoginPage(),
    private val profilePage: ProfilePage = ProfilePage()
) {

    fun verifyLoginScreenDisplayed() = apply { loginPage.assertDisplayed() }

    fun login(username: String, password: String) = apply {
        loginPage.typeUsername(username)
        loginPage.typePassword(password)
        loginPage.tapLogIn()
    }

    fun openRegistration() = apply { loginPage.tapSignUp() }

    fun verifyPasswordError() = apply { loginPage.assertPasswordErrorShown() }

    fun verifyUsernameError() = apply { loginPage.assertUsernameErrorShown() }

    /** Used in @After: does nothing if the app is already on the login screen. */
    fun logOutIfLoggedIn() = apply {
        if (profilePage.isLoggedIn()) {
            profilePage.openProfile()
            profilePage.tapLogOut()
        }
    }
}