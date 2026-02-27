package com.yogi.portfolio.portfolio.domain.model.Request.Responce

data class GeminiRequest(
    val contents: List<Content>
)

data class Content(
    val parts: List<Part>
)

data class Part(
    val text: String
)