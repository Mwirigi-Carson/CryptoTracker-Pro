package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.FormattedNumber
import com.kinyua_carson.cointrackerpro.ui.theme.CryptoTrackerProTheme
import com.kinyua_carson.cointrackerpro.ui.theme.greenBackground

@Composable
fun PriceChange(
    modifier: Modifier = Modifier,
    change: FormattedNumber
) {
    val contentColor = if (change.value < 0) {
        MaterialTheme.colorScheme.onErrorContainer
    } else {
        Color.Green
    }

    val backgroundColor = if (change.value < 0) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        greenBackground
    }

    Row (
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(backgroundColor)
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center
    ){
        Icon(
            imageVector = if(change.value < 0) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
            tint = contentColor
        )

        Text(
            text = "${change.formatted} $",
            fontSize = 12.sp,
            color = contentColor
        )
    }
}

@PreviewLightDark
@Composable
private fun PriceChangePreview() {
    CryptoTrackerProTheme {
        PriceChange(
            change = previewCoin.percentagePriceChange24Hr
        )
    }
}