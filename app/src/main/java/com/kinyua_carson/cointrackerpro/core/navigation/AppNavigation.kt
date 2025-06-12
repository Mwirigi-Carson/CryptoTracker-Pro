package com.kinyua_carson.cointrackerpro.core.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kinyua_carson.cointrackerpro.core.presentation.ObserveAsEvents
import com.kinyua_carson.cointrackerpro.core.presentation.toString
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_details.CoinDetailsScreen
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListEvents
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

    val context = LocalContext.current
    ObserveAsEvents(
        events = viewModel.events
    ) { event ->
        when(event){
            is CoinListEvents.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

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