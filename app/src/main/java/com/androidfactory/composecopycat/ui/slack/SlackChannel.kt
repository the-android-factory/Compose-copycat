package com.androidfactory.composecopycat.ui.slack

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class SlackChannel(
    val name: String,
    val isPrivate: Boolean = false,
    val hasNewMessages: Boolean = false,
    val taggedMessageCount: Int = 0
)

@Composable
fun SlackChannelUI(slackChannel: SlackChannel) {
    val icon = if (slackChannel.isPrivate) Icons.Filled.Lock else Icons.Filled.Tag
    val fontWeight = if (slackChannel.hasNewMessages) FontWeight.Bold else FontWeight.Normal

    Surface(shape = RoundedCornerShape(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(imageVector = icon, contentDescription = "channel-icon")
            Text(
                text = slackChannel.name,
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = fontWeight
            )

            if (slackChannel.taggedMessageCount > 0) {
                Text(
                    text = slackChannel.taggedMessageCount.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp).drawBehind {
                        drawCircle(
                            color = Color.Red,
                            radius = this.size.width * 1.5f
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun SlackChannelUIPreview() {
    Column {
        SlackChannelUI(slackChannel = SlackChannel(name = "channel-one", isPrivate = false))
        DisplaySpace()
        SlackChannelUI(slackChannel = SlackChannel(name = "channel-two", isPrivate = true))
        DisplaySpace()
        SlackChannelUI(
            slackChannel = SlackChannel(
                name = "channel-one",
                isPrivate = false,
                hasNewMessages = true
            )
        )
        DisplaySpace()
        SlackChannelUI(
            slackChannel = SlackChannel(
                name = "channel-two",
                isPrivate = true,
                hasNewMessages = true
            )
        )
        DisplaySpace()
        SlackChannelUI(
            slackChannel = SlackChannel(
                name = "channel-one",
                isPrivate = false,
                hasNewMessages = true,
                taggedMessageCount = 3
            )
        )
        DisplaySpace()
        SlackChannelUI(
            slackChannel = SlackChannel(
                name = "channel-two",
                isPrivate = true,
                hasNewMessages = true,
                taggedMessageCount = 3
            )
        )
    }
}

@Composable
private fun DisplaySpace() {
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .background(Color.Transparent)
    )
}