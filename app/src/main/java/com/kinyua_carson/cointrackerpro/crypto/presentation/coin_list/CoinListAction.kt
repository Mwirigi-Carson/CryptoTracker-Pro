package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

sealed interface CoinListAction {
    data class OnCoinClick(val coinUI: CoinUI): CoinListAction
}