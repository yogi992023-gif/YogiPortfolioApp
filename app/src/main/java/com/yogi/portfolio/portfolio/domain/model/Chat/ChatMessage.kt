package com.yogi.portfolio.portfolio.domain.model.Chat

data class ChatMessage(
    val text: String = "",
    val senderId: String = "",
    val time: Long = 0
)