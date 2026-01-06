package com.yogi.portfolio.portfolio.Analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class AnalyticsHelper @Inject constructor(private val analytics: FirebaseAnalytics) {

    fun logLogin() {
        /*val bundle = Bundle().apply {
            putString("user_id", userId)
        }*/
        analytics.logEvent("login_success", null)
    }

    fun logRegister() {
        analytics.logEvent("register_success", null)

    }

}