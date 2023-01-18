package com.androidfactory.composecopycat.ui.opensea

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.math.RoundingMode

data class OpenSeaCollection(
    val name: String,
    val image: String,
    val rank: Int,
    val isVerified: Boolean,
    val tradeVolumeLast24Hours: BigDecimal,
    val tradePercentChangeLast24Hours: BigDecimal,
    val otherStats: Map<String, String>
)

val OS_Background = Color(0xFF202225)

private fun BigDecimal.scaleForDisplay(newScale: Int = 2): String {
    return this.setScale(newScale, RoundingMode.HALF_UP).toString()
}

@Composable
fun OpenSeaCollectionRankingRow(openSeaCollection: OpenSeaCollection) {

    var isExpanded by remember { mutableStateOf(false) }
    val isPositive = openSeaCollection.tradePercentChangeLast24Hours >= BigDecimal.ZERO

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(color = OS_Background)
            .padding(vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = openSeaCollection.rank.toString(), color = Color.White)
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(75.dp)
            ) {
                AsyncImage(
                    model = openSeaCollection.image,
                    contentDescription = "collection-image",
                    contentScale = ContentScale.Crop
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = openSeaCollection.name,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    if (openSeaCollection.isVerified) {
                        Image(
                            imageVector = Icons.Rounded.Verified,
                            contentDescription = "verified",
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .size(16.dp),
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                    }
                }
                TextButton(onClick = { isExpanded = !isExpanded }) {
                    Text(text = if (isExpanded) "- less" else "+ more", color = Color.LightGray)
                }
            }

            Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(start = 4.dp)) {
                Text(
                    text = openSeaCollection.tradeVolumeLast24Hours.scaleForDisplay() + " ETH",
                    color = Color.White
                )
                Text(
                    text = openSeaCollection.tradePercentChangeLast24Hours.scaleForDisplay() + "%",
                    color = if (isPositive) Color.Green else Color.Red
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Column {
                Spacer(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .background(color = Color.LightGray)
                        .height(0.5.dp)
                        .fillMaxWidth()
                )

                Row {
                    CollectionStatItem(
                        label = "24h%",
                        value = openSeaCollection.tradePercentChangeLast24Hours.scaleForDisplay() + "%",
                        modifier = Modifier.weight(1f),
                        valueColor = if (isPositive) Color.Green else Color.Red
                    )
                    openSeaCollection.otherStats.forEach { mapEntry ->
                        CollectionStatItem(
                            label = mapEntry.key,
                            value = mapEntry.value,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CollectionStatItem(
    label: String,
    value: String,
    valueColor: Color = Color.White,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = label, color = Color.LightGray, fontWeight = FontWeight.Light)
        Text(text = value, color = valueColor)
    }
}

@Preview(showBackground = true)
@Composable
fun OpenSeaCollectionRankingRowPreview() {
    val collectionStats = mapOf(
        "Floor Price" to "15.77 ETH",
        "Owners" to "12,304",
        "Assets" to "19,442"
    )
    val openSeaCollection = OpenSeaCollection(
        name = "Mutant Ape Yacht Club",
        image = "https://i.seadn.io/gae/lHexKRMpw-aoSyB1WdFBff5yfANLReFxHzt1DOj_sg7mS14yARpuvYcUtsyyx-Nkpk6WTcUPFoG53VnLJezYi8hAs0OxNZwlw6Y-dmI?auto=format&w=256",
        rank = 5,
        isVerified = true,
        tradeVolumeLast24Hours = BigDecimal(456.57),
        tradePercentChangeLast24Hours = BigDecimal(57.71),
        otherStats = collectionStats
    )

    Column {
        OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection)
    }
}