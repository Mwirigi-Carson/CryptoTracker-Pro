package com.kinyua_carson.cointrackerpro.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_details.CoinDetailsScreen
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListScreen
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListViewModel
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinsListState

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    state: CoinsListState,
    viewModel: CoinListViewModel,
    startDestination: String = Screens.CoinsListScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(Screens.CoinsListScreen.route){
            val filteredCoins by viewModel.filteredCoins.collectAsStateWithLifecycle()
            CoinListScreen(
                state = state,
                modifier = modifier,
                onAction = viewModel::onAction,
                navController = navController,
                filteredCoins = filteredCoins,
            )
        }

        composable(Screens.CoinDetailScreen.route){
            CoinDetailsScreen(
                state = state,
                modifier = modifier
            )
        }
    }
}