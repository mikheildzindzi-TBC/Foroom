package com.example.foroom.steps

import com.example.foroom.components.HomeBarNavigationComponent
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val homeBar = HomeBarNavigationComponent()
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun openProfile() = apply {
        homeBar.openProfile()
    }

    fun changePassword(newPassword: String) = apply {
        profilePage.tapChangePass()
        changePasswordPage.typeNewPass(newPassword)
        changePasswordPage.typeRepeatNewPass(newPassword)
        changePasswordPage.tapActionButton()
    }

    fun selectGeorgian() = apply {
        profilePage.tapChangeLang()
        changeLanguagePage.tapLangButtonGeo()
    }

    fun selectEnglish() = apply {
        profilePage.tapChangeLang()
        changeLanguagePage.tapLangButtonEng()
    }

    fun verifyProfileLabelDisplayed(label: String) = apply {
        profilePage.assertLabelDisplayed(label)
    }

    fun logOut() = apply {
        profilePage.tapLogOut()
    }

    fun ensureLoggedOut() = apply {
        if (profilePage.isLoggedIn()) {
            openProfile()
            logOut()
        }
    }
}