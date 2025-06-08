package com.kinyua_carson.cointrackerpro.crypto.domain

import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError
import com.kinyua_carson.cointrackerpro.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinListRepository {
    suspend fun getCoins(): Result<List<Coin>,NetworkError>
    suspend fun getCoinHistory(id: String, start: ZonedDateTime, end: ZonedDateTime): Result<List<CoinPrice>, NetworkError>
}