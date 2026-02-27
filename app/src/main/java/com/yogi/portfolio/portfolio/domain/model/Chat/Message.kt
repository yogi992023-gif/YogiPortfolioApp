package com.yogi.portfolio.portfolio.domain.model.Chat

data class Message(
    val senderId: String = "",
    val receiverId: String = "",
    val message: String = "",
    val timestamp: Long = 0
)