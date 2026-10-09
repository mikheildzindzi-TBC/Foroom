package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf
import org.hamcrest.Matchers.not

class ConversationPage {
    val messagesList: Matcher<View> = withId(R.id.messagesRecyclerView)
    private val messageInputId = R.id.messageInput
    private val sendButtonId = R.id.sendMessageButton
    private val senderLabelId = R.id.userNameTextView

    val messageInput: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        anyOf(withId(messageInputId), isDescendantOfA(withId(messageInputId))),
        isDisplayed()
    )

    val sendButton: Matcher<View> = allOf(withId(sendButtonId), isDisplayed())

    fun messageRow(text: String): Matcher<View> = allOf(
        withParent(messagesList),
        hasDescendant(withText(text)),
        isDisplayed()
    )

    fun messageRowFrom(text: String, sender: String): Matcher<View> = allOf(
        withParent(messagesList),
        hasDescendant(withText(text)),
        hasDescendant(allOf(withId(senderLabelId), withText(sender))),
        isDisplayed()
    )

    fun conversationTitle(title: String): Matcher<View> = allOf(
        withText(title),
        not(isDescendantOfA(withId(R.id.chatsRecyclerView))),
        isDisplayed()
    )
}