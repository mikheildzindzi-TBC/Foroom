package com.example.foroom.steps

import com.example.foroom.components.HomeBarNavigationComponent
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val homeBar = HomeBarNavigationComponent()
    private val createChatPage = CreateChatPage()
    private val chatsPage = ChatsPage()

    fun openCreateChatPage() = apply { homeBar.openCreateChatPage() }

    fun createChat(name: String, imagePosition: Int = 0) = apply {
        createChatPage.typeChatName(name)
        createChatPage.selectChatImage(imagePosition)
        createChatPage.tapCreateChatButton()
    }

    fun verifyChatOpened(name: String) = apply { chatsPage.assertChatNameDisplayed(name) }

    fun closeChat() = apply { createChatPage.tapCloseButton() }

    fun searchChat(name: String) = apply { chatsPage.typeInSearch(name) }

    fun verifyChatInList(name: String) = apply { chatsPage.assertChatWithNameDisplayed(name) }
}