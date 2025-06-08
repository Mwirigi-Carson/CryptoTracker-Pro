package com.kinyua_carson.cointrackerpro.core.data.networking

import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError
import com.kinyua_carson.cointrackerpro.core.domain.util.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(httpResponse: HttpResponse): Result<T, NetworkError>{
    return when(httpResponse.status.value){
        in 200..299 -> {
            try {
                Result.Success(httpResponse.body<T>())
            } catch (e: NoTransformationFoundException){
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        400 -> {
            Result.Error(NetworkError.BAD_REQUEST)
        }
        403 -> {
            Result.Error(NetworkError.FORBIDDEN)
        }
        429 -> {
            Result.Error(NetworkError.TOO_MANY_REQUESTS)
        }
        in 500..500 -> {
            Result.Error(NetworkError.SERVER_ERROR)
        }
        10002 -> {
            Result.Error(NetworkError.INCORRECT_API_KEY)
        }
        else -> {
            Result.Error(NetworkError.UN_KNOWN)
        }
    }
}