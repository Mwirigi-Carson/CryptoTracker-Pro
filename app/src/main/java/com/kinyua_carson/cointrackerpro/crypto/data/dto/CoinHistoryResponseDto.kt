package com.kinyua_carson.cointrackerpro.crypto.data.dto


import com.kinyua_carson.cointrackerpro.crypto.domain.CoinPrice
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId

@Serializable
data class CoinHistoryResponseDto(
    @SerialName("market_caps")
    val marketCaps: List<List<Double?>?>? = null,
    @SerialName("prices")
    val prices: List<List<Double?>?>? = null,
    @SerialName("total_volumes")
    val totalVolumes: List<List<Double?>?>? = null
)

fun CoinHistoryResponseDto.toCoinPrices(): List<CoinPrice> {
    return this.prices?.mapNotNull { dataPoint ->
        val timeStamp = dataPoint?.get(0)?.toLong()
        val price = dataPoint?.get(1)
        if (timeStamp != null && price != null){
            CoinPrice(
                time = Instant
                    .ofEpochMilli(timeStamp)
                    .atZone(ZoneId.of("UTC")),
                price = price
            )
        } else null
    } ?: emptyList()
}