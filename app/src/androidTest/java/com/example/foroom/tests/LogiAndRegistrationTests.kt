package com.example.foroom.tests

import com.example.foroom.presentation.ui.activity.ForoomActivity
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.constants.Constants.EXISTING_USERNAME
import com.example.foroom.constants.Constants.NON_EXISTING_USERNAME
import com.example.foroom.constants.Constants.USERNAME_PREFIX
import com.example.foroom.constants.Constants.VALID_PASSWORD
import com.example.foroom.constants.Constants.WRONG_PASSWORD
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private lateinit var loginSteps: LoginSteps
    private lateinit var registrationSteps: RegistrationSteps

    @Before
    fun setUp() {
        loginSteps = LoginSteps()
        registrationSteps = RegistrationSteps()
        loginSteps.logOutIfLoggedIn()
    }

    @Test
    fun validUsernameAndInvalidPassword_showsPasswordError() {
        loginSteps
            .verifyLoginScreenDisplayed()
            .login(EXISTING_USERNAME, WRONG_PASSWORD)
            .verifyPasswordError()
    }

    @Test
    fun invalidUsernameAndInvalidPassword_showsBothErrors() {
        loginSteps
            .verifyLoginScreenDisplayed()
            .login(NON_EXISTING_USERNAME, WRONG_PASSWORD)
            .verifyUsernameError()
            .verifyPasswordError()
    }

    @Test
    fun successfulRegistration_opensHomeScreen() {
        loginSteps
            .verifyLoginScreenDisplayed()
            .openRegistration()

        registrationSteps
            .verifyRegistrationScreenDisplayed()
            .register(uniqueUsername(), VALID_PASSWORD)
            .verifyHomeScreenDisplayed()
    }

    private fun uniqueUsername() = USERNAME_PREFIX + System.currentTimeMillis().toString().takeLast(9)
}