package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class CreateChatPage {

    val chatName: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.chatNameInput)),
        isDisplayed()
    )

    val chatImageChooser: Matcher<View> = allOf(
        withId(R.id.chatImageChooser),
        isDisplayed()
    )

    val createChatButton: Matcher<View> = allOf(
        withId(R.id.createChatButton),
        isDisplayed()
    )

    val closeButton: Matcher<View> = allOf(
        withId(R.id.closeButton),
        isDisplayed()
    )
}