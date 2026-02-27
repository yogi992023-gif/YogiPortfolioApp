package com.yogi.portfolio.portfolio.ui.composable

import ChatViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yogi.portfolio.portfolio.domain.model.Chat.User

@Composable
fun UserListScreen(
    viewModel: ChatViewModel,
    onUserClick: (User) -> Unit
) {

    val users = viewModel.users

    LaunchedEffect(Unit) {
        viewModel.loadUsers()
    }

    LazyColumn {

        items(users) { user ->

            UserItem(user) {
                onUserClick(user)
            }
        }
    }
}

@Composable
fun UserItem(user: User, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            }
    ) {

        Text(
            text = user.name,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun UserListScreenPreview() {


    // Now the preview works perfectly because it doesn't need a real ViewModel
}