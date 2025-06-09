package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kinyua_carson.cointrackerpro.core.navigation.Screens
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.components.CoinListItem
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.components.SearchCoin

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinsListState,
    filteredCoins: List<CoinUI>,
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
    } else if(filteredCoins.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    SearchCoin(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        onSearch = { query ->
                            onAction(CoinListAction.OnSearchQuery(query))
                        }
                    )
                }
                items(filteredCoins){ coinUi ->
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