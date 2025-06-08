package com.kinyua_carson.cointrackerpro.crypto.domain

import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinUI
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.toFormattedNumber

data class Coin(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPriceUsd: Double,
    val marketCapUsd: Double,
    val percentagePriceChange24Hr: Double
)

fun Coin.toCoinUi() : CoinUI {
    return CoinUI(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        image = image,
        currentPriceUsd = currentPriceUsd.toFormattedNumber(),
        marketCapUsd = marketCapUsd.toFormattedNumber(),
        percentagePriceChange24Hr = percentagePriceChange24Hr.toFormattedNumber()
    )
}
