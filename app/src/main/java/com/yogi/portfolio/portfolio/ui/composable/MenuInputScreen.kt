package com.yogi.portfolio.portfolio.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MenuInputScreen(
    onSubmit: (String, Int) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var iconText by remember { mutableStateOf("") }
    var iconRes by remember { mutableStateOf<Int?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Menu Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = iconText,
            onValueChange = {
                iconText = it
                val resId = context.resources.getIdentifier(
                    iconText,
                    "drawable",
                    context.packageName
                )
                iconRes = if (resId != 0) resId else null
            },
            label = { Text("Icon Resource Name") },
            placeholder = { Text("example: ic_cart") },
            trailingIcon = {
                iconRes?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { onSubmit(title, iconRes ?: 0) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuInputScreenPreview() {
    MenuInputScreen(
        onSubmit = { _, _ -> }
    )
}