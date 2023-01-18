package com.androidfactory.composecopycat.ui.opensea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

class OpenSeaCollectionRankingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val collectionStats = mapOf(
                "Floor Price" to "15.77 ETH",
                "Owners" to "12,304",
                "Assets" to "19,442"
            )
            val collectionStats2 = mapOf(
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

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Gray)
            ) {
                OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection.copy(rank = 1, otherStats = collectionStats2))
                Spacer(modifier = Modifier.height(1.dp))
                OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection.copy(rank = 2))
                Spacer(modifier = Modifier.height(1.dp))
                OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection.copy(rank = 3))
                Spacer(modifier = Modifier.height(1.dp))
                OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection.copy(rank = 4))
                Spacer(modifier = Modifier.height(1.dp))
                OpenSeaCollectionRankingRow(openSeaCollection = openSeaCollection)
            }
        }
    }
}