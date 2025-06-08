package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.kinyua_carson.cointrackerpro.core.navigation.Screens
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinsListState,
    onAction: (CoinListAction) -> Unit,
    navController: NavHostController
) {
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
    } else {
        if (state.coins.isNotEmpty()){
            LazyColumn (
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(state.coins){ coinUi ->
                    CoinListItem(
                        coinUI = coinUi,
                        onClick = {
                            onAction(CoinListAction.OnCoinClick(coinUi))
                            navController.navigate(Screens.CoinDetailScreen.route)
                        }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}