package com.kinyua_carson.cointrackerpro.core.navigation

sealed class Screens(val route: String) {
    data object CoinsListScreen: Screens("coins_list_screen")
    data object CoinDetailScreen: Screens("coin_detail_screen")
}