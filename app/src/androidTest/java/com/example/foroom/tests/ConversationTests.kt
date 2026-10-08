package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.constants.Constants.DRINK_MESSAGE
import com.example.foroom.constants.Constants.FILLER_MESSAGE_COUNT
import com.example.foroom.constants.Constants.FILLER_PREFIX
import com.example.foroom.constants.Constants.GREETING
import com.example.foroom.constants.Constants.JOHN_WEEK_CHAT
import com.example.foroom.constants.Constants.QUESTION
import com.example.foroom.constants.Constants.REPLY
import com.example.foroom.constants.Constants.SHARED_CHAT
import com.example.foroom.constants.Constants.USER_A
import com.example.foroom.constants.Constants.USER_B
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.utils.SwipeDirection
import com.example.foroom.utils.TestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()
    private val conversationSteps = ConversationSteps()

    private val johnWeekChat = TestData.unique(JOHN_WEEK_CHAT)
    private val ownNameChat = TestData.uniqueChatName()
    private val sharedChat = TestData.unique(SHARED_CHAT)

    private val message = TestData.unique(DRINK_MESSAGE)
    private val question = TestData.unique(QUESTION)
    private val greeting = TestData.unique(GREETING)
    private val reply = TestData.unique(REPLY)

    @Before
    fun startFromLoginScreen() {
        profileSteps.ensureLoggedOut()
        loginSteps.verifyLoginScreenDisplayed()
    }

    @Test
    fun sendAMessageInJohnWeek() {
        loginSteps
            .login(USER_A.username, USER_A.password)
            .verifyHomeScreenDisplayed()

        chatSteps
            .openCreateChatPage()
            .createChat(johnWeekChat)

        conversationSteps
            .verifyConversationOpen(johnWeekChat)
            .sendMessage(message)
            .verifyMessageDisplayed(message)
            .closeChat()

        chatSteps
            .searchChat(johnWeekChat)
            .tapChat(johnWeekChat)

        conversationSteps
            .verifyConversationOpen(johnWeekChat)
            .verifyMessageDisplayed(message)
    }

    @Test
    fun sendAQuestionInYourOwnChat() {
        loginSteps
            .login(USER_A.username, USER_A.password)
            .verifyHomeScreenDisplayed()

        chatSteps
            .openCreateChatPage()
            .createChat(ownNameChat)

        conversationSteps
            .verifyConversationOpen(ownNameChat)
            .sendMessage(question)
            .verifyMessageDisplayed(question)
    }

    @Test
    fun continueAConversationUsingAnotherAccount() {
        loginSteps
            .login(USER_A.username, USER_A.password)
            .verifyHomeScreenDisplayed()

        chatSteps
            .openCreateChatPage()
            .createChat(sharedChat)

        conversationSteps
            .verifyConversationOpen(sharedChat)
            .sendMessage(greeting)
            .verifyMessageDisplayed(greeting)
            .sendMessages(FILLER_PREFIX, FILLER_MESSAGE_COUNT)
            .verifyMessageOutsideVisibleArea(greeting)
            .closeChat()

        profileSteps.openProfile().logOut()
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps
            .login(USER_B.username, USER_B.password)
            .verifyHomeScreenDisplayed()

        chatSteps
            .searchChat(sharedChat)
            .tapChat(sharedChat)

        conversationSteps
            .verifyConversationOpen(sharedChat)
            .scrollToMessage(greeting, SwipeDirection.TO_OLDER)
            .verifySender(greeting, USER_A.username)
            .sendMessage(reply)
            .scrollToMessage(reply, SwipeDirection.TO_NEWER) // no-op if already visible
            .verifyMessageDisplayed(reply)
            .closeChat()

        profileSteps.openProfile().logOut()
        loginSteps.verifyLoginScreenDisplayed()

        loginSteps
            .login(USER_A.username, USER_A.password)
            .verifyHomeScreenDisplayed()

        chatSteps
            .searchChat(sharedChat)
            .tapChat(sharedChat)

        conversationSteps
            .verifyConversationOpen(sharedChat)
            .scrollToMessage(reply, SwipeDirection.TO_NEWER)
            .verifySender(reply, USER_B.username)
    }
}