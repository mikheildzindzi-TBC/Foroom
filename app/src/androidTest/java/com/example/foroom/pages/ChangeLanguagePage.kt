package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf

class ChangeLanguagePage {
    private val langButtonGeo = allOf(withId(R.id.languageButtonGeo), isDisplayed())
    private val langButtonEng = allOf(withId(R.id.languageButtonEng), isDisplayed())

    fun tapLangButtonGeo(){
        onView(langButtonGeo).perform(click())
    }

    fun tapLangButtonEng(){
        onView(langButtonEng).perform(click())
    }
}