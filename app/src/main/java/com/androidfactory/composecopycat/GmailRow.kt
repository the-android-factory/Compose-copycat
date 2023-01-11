package com.androidfactory.composecopycat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class GmailRow(
    val sender: Sender,
    val subject: String,
    val body: String,
    val deliveryDate: String,
    val isUnread: Boolean,
    val isStarred: Boolean
) {
    data class Sender(val name: String, val color: Color)
}

@Composable
fun GmailRowItem(gmailRow: GmailRow) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(IntrinsicSize.Min)
    ) {
        Column {
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(30.dp),
                color = gmailRow.sender.color
            ) {
                Text(
                    text = gmailRow.sender.name.first().toString(),
                    modifier = Modifier.wrapContentSize()
                )
            }
        }

        val weight = if (gmailRow.isUnread) FontWeight.Bold else FontWeight.Light
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ) {
            Text(text = gmailRow.sender.name, fontWeight = weight)
            Text(text = gmailRow.subject, fontWeight = weight)
            Text(
                text = gmailRow.body,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Light
            )
        }

        val icon = if (gmailRow.isStarred) Icons.Filled.Star else Icons.Filled.StarBorder
        Column(horizontalAlignment = Alignment.End) {
            Text(text = gmailRow.deliveryDate)
            Spacer(modifier = Modifier.weight(1f))
            Image(imageVector = icon, contentDescription = "starred-status")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GmailRowItemPreview() {
    val sender = GmailRow.Sender(
        name = "Android Factory",
        color = Color.Blue.copy(alpha = .2f)
    )
    val row = GmailRow(
        sender = sender,
        subject = "Thanks for stopping by!",
        body = "Don't forget to like, comment, and subscribe to help me grow",
        deliveryDate = "Jan 10",
        isUnread = false,
        isStarred = false
    )

    Column {
        GmailRowItem(gmailRow = row)
        GmailRowItem(gmailRow = row.copy(isStarred = true))
        GmailRowItem(gmailRow = row.copy(isUnread = true))
        GmailRowItem(gmailRow = row.copy(isUnread = true, isStarred = true))
    }
}