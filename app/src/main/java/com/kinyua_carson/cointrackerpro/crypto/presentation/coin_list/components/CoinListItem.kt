package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kinyua_carson.cointrackerpro.crypto.domain.Coin
import com.kinyua_carson.cointrackerpro.crypto.domain.toCoinUi
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinUI
import com.kinyua_carson.cointrackerpro.ui.theme.CryptoTrackerProTheme

@Composable
fun CoinListItem(
    coinUI: CoinUI,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black
    Row (
        modifier = modifier
            .clickable (onClick = {
                onClick()
                Log.d("CoinListItem", "clicked")
            })
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = coinUI.image,
            contentDescription = null,
            modifier = Modifier.size(72.dp)
        )
        Column (
            modifier = Modifier
                .weight(1f)
        ){
            Text(
                text = coinUI.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = contentColor
            )
            Text(
                text = coinUI.symbol,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
        Column (
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = coinUI.currentPriceUsd.formatted,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = contentColor
            )
            Spacer(
                Modifier.height(8.dp)
            )
            PriceChange(
                change = coinUI.percentagePriceChange24Hr
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    CryptoTrackerProTheme {
        CoinListItem(
            coinUI = previewCoin,
            onClick = {}
        )
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    rank = 1,
    symbol = "BTC",
    name = "Bitcoin",
    image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png",
    currentPriceUsd = 68900.75,
    marketCapUsd = 1350000000000.0,
    percentagePriceChange24Hr = 2.15
).toCoinUi()