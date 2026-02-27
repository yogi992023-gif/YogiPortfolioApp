package com.yogi.portfolio.portfolio.ui.composable

import ChatViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.yogi.portfolio.portfolio.domain.model.Chat.Message

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    receiverId: String
) {

    var messageText by remember { mutableStateOf("") }

    val messages = viewModel.messages

    LaunchedEffect(Unit) {
        viewModel.listenMessages(receiverId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(messages) { message ->

                MessageItem(message)
            }
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {

            TextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier.weight(1f)
            )

            Button(onClick = {

                viewModel.sendMessage(receiverId, messageText)
                messageText = ""

            }) {
                Text("Send")
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {

    val currentUser = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val alignment =
        if (message.senderId == currentUser)
            Alignment.CenterEnd
        else
            Alignment.CenterStart

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = alignment
    ) {

        Card {

            Text(
                text = message.message,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
