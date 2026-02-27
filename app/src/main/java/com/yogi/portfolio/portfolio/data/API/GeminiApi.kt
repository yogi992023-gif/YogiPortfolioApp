package com.yogi.portfolio.portfolio.data.API

import com.yogi.portfolio.portfolio.domain.model.Request.Responce.GeminiRequest
import com.yogi.portfolio.portfolio.domain.model.Request.Responce.GeminiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GeminiApi {

    @POST("v1beta/models/gemini-2.0-flash:generateContent")
    suspend fun chat(
        @Query("key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}