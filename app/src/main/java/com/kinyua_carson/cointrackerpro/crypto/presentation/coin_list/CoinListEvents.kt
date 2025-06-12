package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError

interface CoinListEvents {
    data class Error(val error: NetworkError): CoinListEvents
}