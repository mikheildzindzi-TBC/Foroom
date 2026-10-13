package com.example.foroom.utils

import com.example.foroom.constants.Constants.FULL_NAME

object TestData {

    fun unique(base: String): String = "$base ${System.currentTimeMillis()}"

    fun uniqueChatName(): String = unique(FULL_NAME)
}