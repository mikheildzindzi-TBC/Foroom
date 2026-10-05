package com.example.foroom.utils

import com.example.foroom.constants.Constants.FULL_NAME

object TestData {

    fun uniqueChatName(): String = "$FULL_NAME ${System.currentTimeMillis()}"
}