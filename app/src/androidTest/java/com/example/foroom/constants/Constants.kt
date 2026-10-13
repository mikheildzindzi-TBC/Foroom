package com.example.foroom.constants

object Constants {

    data class TestUser(val username: String, val password: String)

    val USER_A = TestUser("TestUserA", "TestPassA")
    val USER_B = TestUser("TestUserB", "TestPassB")

    const val JOHN_WEEK_CHAT = "johnWeek"

    const val SHARED_CHAT = "something"

    const val DRINK_MESSAGE = "let's go for a drink"
    const val QUESTION = "Which module do you like in the Automation Academy?"
    const val GREETING = "Gamarjoba from User A"
    const val REPLY = "Gagimarjos from User B"
    const val FILLER_PREFIX = "kidev 25 text message"

    const val FILLER_MESSAGE_COUNT = 26

    const val FULL_NAME = "MikheilDzindzibadze"
    const val IMAGE_CHOOSER_ITEM_CLASS = "ImageChooserItemView"
}