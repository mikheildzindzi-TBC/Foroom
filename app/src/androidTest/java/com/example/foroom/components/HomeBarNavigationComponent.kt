package com.example.foroom.components

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf

class HomeBarNavigationComponent {
    private val profileTab = allOf(withId(R.id.homeNavigationProfile), isDisplayed())
    private val createChatButton = allOf(withId(R.id.homeNavigationCreateChat), isDisplayed())
    private val chatTab = allOf(withId(R.id.homeNavigationChats), isDisplayed())

    fun openProfile() {
        onView(profileTab).perform(click())
    }

    fun openCreateChatPage(){
        onView(createChatButton).perform(click())
    }

    fun openChatTab(){
        onView(chatTab).perform(click())
    }
}