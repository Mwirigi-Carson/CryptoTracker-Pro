package com.kinyua_carson.cointrackerpro.core.domain.util

enum class NetworkError: Error {
    BAD_REQUEST,
    FORBIDDEN,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    INCORRECT_API_KEY,
    UN_KNOWN,
    SERIALIZATION,
    NO_INTERNET
}