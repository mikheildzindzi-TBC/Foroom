package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.constants.Constants.ENG_CHANGE_LANG
import com.example.foroom.constants.Constants.ENG_LOGOUT
import com.example.foroom.constants.Constants.FULL_NAME
import com.example.foroom.constants.Constants.GEO_CHANGE_LANG
import com.example.foroom.constants.Constants.GEO_LOGOUT
import com.example.foroom.constants.Constants.NEW_PASSWORD
import com.example.foroom.constants.Constants.TEST_PASSWORD
import com.example.foroom.constants.Constants.TEST_USERNAME
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.foroom.utils.TestData

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()
    private val chatName = TestData.uniqueChatName()

    @Before
    fun startFromLoginScreen() {
        profileSteps.ensureLoggedOut()
        loginSteps.verifyLoginScreenDisplayed()
    }

    @Test
    fun changePassword_thenLoginWithNewPassword() {
        loginSteps
            .login(TEST_USERNAME, TEST_PASSWORD)
            .verifyHomeScreenDisplayed()

        profileSteps
            .openProfile()
            .changePassword(NEW_PASSWORD)

        loginSteps.verifyLoginScreenDisplayed()

        loginSteps
            .login(TEST_USERNAME, NEW_PASSWORD)
            .verifyHomeScreenDisplayed()

        // restore the original password so the test can be rerun and other tests still work
        profileSteps
            .openProfile()
            .changePassword(TEST_PASSWORD)
        loginSteps.verifyLoginScreenDisplayed()
    }

    @Test
    fun changeLanguage_georgianToEnglishAndBack() {
        loginSteps
            .login(TEST_USERNAME, TEST_PASSWORD)
            .verifyHomeScreenDisplayed()

        profileSteps
            .openProfile()
            .selectGeorgian()
            .verifyProfileLabelDisplayed(GEO_CHANGE_LANG)
            .verifyProfileLabelDisplayed(GEO_LOGOUT)

        profileSteps
            .selectEnglish()
            .verifyProfileLabelDisplayed(ENG_CHANGE_LANG)
            .verifyProfileLabelDisplayed(ENG_LOGOUT)

        profileSteps
            .selectGeorgian()
            .verifyProfileLabelDisplayed(GEO_CHANGE_LANG)
            .verifyProfileLabelDisplayed(GEO_LOGOUT)
    }

    @Test
    fun createChat_thenFindItInChatList() {
        loginSteps
            .login(TEST_USERNAME, TEST_PASSWORD)
            .verifyHomeScreenDisplayed()

        chatSteps
            .openCreateChatPage()
            .createChat(chatName, imagePosition = 0)
            .verifyChatOpened(chatName)
            .closeChat()
            .searchChat(chatName)
            .verifyChatInList(chatName)
    }
}