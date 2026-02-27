package com.yogi.portfolio.portfolio.domain.model.Chat

data class Chat(
    val chatId: String = "",
    val userIds: List<String> = listOf(),
    val lastMessage: String = "",
    val lastTime: Long = 0
)