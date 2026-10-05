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

class ChangePasswordPage {
    private fun input(parentId: Int, childId: Int): Matcher<View> =
        allOf(withId(childId), isDescendantOfA(allOf(withId(parentId), isDisplayed())))

    private val newPassField = input(R.id.passwordInput, DS.id.inputEditText)
    private val repeatNewPassField = input(R.id.repeatPasswordInput, DS.id.inputEditText)
    private val actionButton = allOf(withId(DS.id.actionButton), isDisplayed())

    fun typeNewPass(value: String) {
        onView(newPassField).perform(replaceText(value))
    }

    fun typeRepeatNewPass(value: String){
        onView(repeatNewPassField).perform(replaceText(value))
    }

    fun tapActionButton() {
        onView(actionButton).perform(click())
    }
}