package com.kinyua_carson.cointrackerpro.crypto.data

import com.kinyua_carson.cointrackerpro.BuildConfig
import com.kinyua_carson.cointrackerpro.core.data.networking.constructUrl
import com.kinyua_carson.cointrackerpro.core.data.networking.safeCall
import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError
import com.kinyua_carson.cointrackerpro.core.domain.util.Result
import com.kinyua_carson.cointrackerpro.core.domain.util.map
import com.kinyua_carson.cointrackerpro.crypto.data.dto.CoinHistoryResponseDto
import com.kinyua_carson.cointrackerpro.crypto.data.dto.CoinsResponseDtoItem
import com.kinyua_carson.cointrackerpro.crypto.data.dto.toCoin
import com.kinyua_carson.cointrackerpro.crypto.data.dto.toCoinPrices
import com.kinyua_carson.cointrackerpro.crypto.domain.Coin
import com.kinyua_carson.cointrackerpro.crypto.domain.CoinListRepository
import com.kinyua_carson.cointrackerpro.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinListRepositoryImpl(
    private val client: HttpClient
): CoinListRepository {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall <List<CoinsResponseDtoItem>>{
            client.get(
                urlString = constructUrl("/coins/markets")
            ) {
                url {
                    parameters.append("vs_currency", "usd")
                    parameters.append("per_page", "${250}")
                }
                header(key = "x-cg-demo-api-key", value = BuildConfig.API_KEY)
            }
        }.map { coinsResponseDtoItems ->
            coinsResponseDtoItems.map { coinsResponseDtoItem ->
                coinsResponseDtoItem.toCoin()
            }
        }
    }

    override suspend fun getCoinHistory(
        id: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall <CoinHistoryResponseDto>{
            client.get(
                urlString = constructUrl("/coins/${id}/market_chart/range")
            ){
                url {
                    parameters.append("vs_currency", "usd")
                    parameters.append("from", endMillis.toString())
                    parameters.append("to", startMillis.toString())
                }
                header(key = "x-cg-demo-api-key", value = BuildConfig.API_KEY)
            }
        }.map { coinHistoryResponseDto ->
            coinHistoryResponseDto.toCoinPrices()
        }
    }
}