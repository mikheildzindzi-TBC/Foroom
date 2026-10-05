package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import com.alternator.foroom.R
import com.example.foroom.utils.waitUntilDisplayed
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matcher

class ChatsPage {
    private val searchEditText: ViewInteraction
        get() = onView(
            allOf(
                isAssignableFrom(EditText::class.java),
                isDescendantOfA(withId(R.id.searchChatInput))
            )
        )

    private val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    fun typeInSearch(text: String) {
        searchEditText.perform(replaceText(text), closeSoftKeyboard())
    }

    private fun chatCard(name: String): Matcher<View> = allOf(
        withText(name),
        isDescendantOfA(chatsRecyclerView),
        isDisplayed()
    )

    private fun chatTitle(name: String): Matcher<View> = allOf(
        withText(name),
        isDisplayed()
    )

    fun assertChatWithNameDisplayed(chatName: String) {
        waitUntilDisplayed(chatCard(chatName))
        onView(chatCard(chatName)).check(matches(isDisplayed()))
    }

    fun assertChatNameDisplayed(name: String) {
        waitUntilDisplayed(chatTitle(name))
        onView(chatTitle(name)).check(matches(isDisplayed()))
    }
}