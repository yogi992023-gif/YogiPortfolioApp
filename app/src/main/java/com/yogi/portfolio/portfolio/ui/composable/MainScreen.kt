package com.yogi.portfolio.portfolio.ui.composable

import ChatViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.yogi.portfolio.portfolio.domain.model.Chat.User

@Composable
fun MainScreen(viewModel: ChatViewModel) {

    var selectedUser by remember { mutableStateOf<User?>(null) }

    if (selectedUser == null) {

        UserListScreen(viewModel) {
            selectedUser = it
        }

    } else {

       /* ChatScreen(
            receiver = selectedUser!!,
            viewModel = viewModel
        )*/
    }
}