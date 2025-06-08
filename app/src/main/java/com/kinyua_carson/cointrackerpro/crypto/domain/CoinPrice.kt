package com.kinyua_carson.cointrackerpro.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val time: ZonedDateTime,
    val price: Double
)
