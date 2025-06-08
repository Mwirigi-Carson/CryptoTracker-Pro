package com.kinyua_carson.cointrackerpro.core.data.networking

import com.kinyua_carson.cointrackerpro.BuildConfig

fun constructUrl(url: String): String {
    return when{
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        url.contains(BuildConfig.BASE_URL) -> url
        else -> BuildConfig.BASE_URL + url
    }
}
