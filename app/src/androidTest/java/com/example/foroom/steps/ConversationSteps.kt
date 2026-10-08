package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.pages.ConversationPage
import com.example.foroom.utils.ListScroller
import com.example.foroom.utils.SwipeDirection
import com.example.foroom.utils.TestData.unique
import com.example.foroom.utils.waitUntilDisplayed

class ConversationSteps {
    private val chatSteps = ChatSteps()
    private val page = ConversationPage()

    fun typeMessage(text: String) = apply {
        waitUntilDisplayed(page.messageInput)
        onView(page.messageInput).perform(replaceText(text), closeSoftKeyboard())
    }

    fun tapSend() = apply {
        waitUntilDisplayed(page.sendButton)
        onView(page.sendButton).perform(click())
    }

    // Swipes the list until the message shows; bounded, fails if it never appears.
    fun scrollToMessage(text: String, direction: SwipeDirection = SwipeDirection.TO_OLDER) = apply {
        ListScroller.swipeUntilDisplayed(page.messagesList, page.messageRow(text), direction)
    }

    fun closeChat() = apply { chatSteps.closeChat() }

    fun verifyConversationOpen(title: String) = apply {
        val titleView = page.conversationTitle(title)
        waitUntilDisplayed(titleView)
        onView(titleView).check(matches(isDisplayed()))
    }

    fun verifyMessageDisplayed(text: String) = apply {
        val row = page.messageRow(text)
        waitUntilDisplayed(row)
        onView(row).check(matches(isDisplayed()))
    }

    fun verifyMessageOutsideVisibleArea(text: String) = apply {
        onView(page.messageRow(text)).check(doesNotExist())
    }

    fun verifySender(text: String, sender: String) = apply {
        val row = page.messageRowFrom(text, sender)
        waitUntilDisplayed(row)
        onView(row).check(matches(isDisplayed()))
    }

    fun sendMessage(text: String) = apply {
        typeMessage(text)
        tapSend()
    }

    fun sendMessages(prefix: String, count: Int) = apply {
        repeat(count) { i ->
            val text = unique("$prefix ${i + 1}")
            sendMessage(text)
            verifyMessageDisplayed(text)
        }
    }
}