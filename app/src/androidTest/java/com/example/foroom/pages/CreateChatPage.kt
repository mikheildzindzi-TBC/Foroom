package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import com.example.design_system.R as DS
import com.example.foroom.utils.CustomViewActions.clickDescendantOfClass
import com.example.foroom.constants.Constants.IMAGE_CHOOSER_ITEM_CLASS



class CreateChatPage {
    private fun input(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(allOf(withId(parentId), isDisplayed())))

    private val chatName = input(R.id.chatNameInput, DS.id.inputEditText)
    private val chatImageChooser = allOf(withId(R.id.chatImageChooser), isDisplayed())
    private val createChatButton = allOf(withId(R.id.createChatButton), isDisplayed())
    private val closeButton = allOf(withId(R.id.closeButton), isDisplayed())

    fun typeChatName(value: String) {
        onView(chatName).perform(replaceText(value))
    }

    fun selectChatImage(position: Int) = apply {
        onView(chatImageChooser).perform(clickDescendantOfClass(IMAGE_CHOOSER_ITEM_CLASS, position))
    }

    fun tapCreateChatButton() {
        onView(createChatButton).perform(click())
    }

    fun tapCloseButton() {
        onView(closeButton).perform(click())
    }
}