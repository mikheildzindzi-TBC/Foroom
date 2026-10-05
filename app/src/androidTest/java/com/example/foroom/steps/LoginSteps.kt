package com.example.foroom.steps

import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage

class LoginSteps {
    private val loginPage = LoginPage()
    private val profilePage = ProfilePage()

    fun login(username: String, password: String) = apply {
        loginPage.assertDisplayed()
        loginPage.typeUsername(username)
        loginPage.typePassword(password)
        loginPage.tapLogIn()
    }

    fun verifyLoginScreenDisplayed() = apply {
        loginPage.assertDisplayed()
    }

    fun verifyHomeScreenDisplayed() = apply {
        profilePage.assertHomeDisplayed()
    }
}