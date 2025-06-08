package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

data class CoinsListState (
    val isLoading: Boolean = false,
    val coins: List<CoinUI> = emptyList(),
    val selectedCoinUI: CoinUI? = null
)