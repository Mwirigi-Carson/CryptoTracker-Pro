package com.kinyua_carson.cointrackerpro.crypto.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Roi(
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("percentage")
    val percentage: Double? = null,
    @SerialName("times")
    val times: Double? = null
)