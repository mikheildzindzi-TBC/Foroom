package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import androidx.test.espresso.matcher.ViewMatchers.withResourceName

class ChatsPage {

    val searchEditText: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.searchChatInput))
    )

    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    fun chatCard(name: String): Matcher<View> = allOf(
        withText(name),
        isDescendantOfA(chatsRecyclerView),
        isDisplayed()
    )

    fun openChatButton(name: String): Matcher<View> = allOf(
        withId(R.id.sendMessageButton),
        hasSibling(allOf(withResourceName("chatTitleTextView"), withText(name))),
        isDisplayed()
    )
}