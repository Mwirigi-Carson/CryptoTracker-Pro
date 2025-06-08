package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import android.icu.text.NumberFormat
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPriceUsd: FormattedNumber,
    val marketCapUsd: FormattedNumber,
    val percentagePriceChange24Hr: FormattedNumber
)

data class FormattedNumber(
    val value: Double,
    val formatted: String
)

fun Double.toFormattedNumber(): FormattedNumber {

    val formatter = NumberFormat
        .getNumberInstance(Locale.getDefault()).apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }

    return FormattedNumber(
        value = this,
        formatted = formatter.format(this)
    )
}
