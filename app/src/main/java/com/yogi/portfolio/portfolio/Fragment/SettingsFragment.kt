package com.yogi.portfolio.portfolio.Fragment

import ChatViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.yogi.portfolio.portfolio.ui.composable.ChatScreen
import com.yogi.portfolio.portfolio.ui.composable.UserListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // Simple navigation state within the fragment
                var selectedUserId by remember { mutableStateOf<String?>(null) }

                if (selectedUserId == null) {
                    UserListScreen(viewModel) { user ->
                        selectedUserId = user.userId
                    }
                } else {
                    ChatScreen(viewModel, selectedUserId!!)
                }
            }
        }
    }
}
