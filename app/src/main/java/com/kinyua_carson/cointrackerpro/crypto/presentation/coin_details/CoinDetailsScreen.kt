package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_details

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinsListState

@Composable
fun CoinDetailsScreen(
    state: CoinsListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black

    if (state.isLoading){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator(
                modifier = Modifier.size(84.dp)
            )
        }
    } else if (state.selectedCoinUI != null){
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = state.selectedCoinUI.image,
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = state.selectedCoinUI.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = contentColor
            )
            Text(
                text = state.selectedCoinUI.symbol,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
        }
    }
}