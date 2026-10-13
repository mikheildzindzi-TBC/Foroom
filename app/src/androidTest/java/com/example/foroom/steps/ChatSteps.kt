package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.components.HomeBarNavigationComponent
import com.example.foroom.constants.Constants.IMAGE_CHOOSER_ITEM_CLASS
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.utils.CustomViewActions.clickDescendantOfClass
import com.example.foroom.utils.waitUntilDisplayed
import org.hamcrest.Matcher

class ChatSteps {
    private val homeBar = HomeBarNavigationComponent()
    private val createChatPage = CreateChatPage()
    private val chatsPage = ChatsPage()

    private fun tap(element: Matcher<View>) {
        waitUntilDisplayed(element)
        onView(element).perform(click())
    }

    fun openCreateChatPage() = apply { homeBar.openCreateChatPage() }

    fun typeChatName(name: String) = apply {
        waitUntilDisplayed(createChatPage.chatName)
        onView(createChatPage.chatName).perform(replaceText(name), closeSoftKeyboard())
    }

    fun selectChatImage(position: Int = 0) = apply {
        waitUntilDisplayed(createChatPage.chatImageChooser)
        onView(createChatPage.chatImageChooser)
            .perform(clickDescendantOfClass(IMAGE_CHOOSER_ITEM_CLASS, position))
    }

    fun tapCreateChatButton() = apply { tap(createChatPage.createChatButton) }

    fun createChat(name: String, imagePosition: Int = 0) = apply {
        typeChatName(name)
        selectChatImage(imagePosition)
        tapCreateChatButton()
    }

    fun closeChat() = apply { tap(createChatPage.closeButton) }

    fun searchChat(name: String) = apply {
        onView(chatsPage.searchEditText).perform(replaceText(name), closeSoftKeyboard())
    }

    fun verifyChatInList(name: String) = apply {
        val card = chatsPage.chatCard(name)
        waitUntilDisplayed(card)
        onView(card).check(matches(isDisplayed()))
    }

    fun tapChat(name: String) = apply {
        val openButton = chatsPage.openChatButton(name)
        waitUntilDisplayed(openButton)
        onView(openButton).perform(click())
    }
}