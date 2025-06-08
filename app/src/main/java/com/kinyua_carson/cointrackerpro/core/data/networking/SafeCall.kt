package com.kinyua_carson.cointrackerpro.core.data.networking

import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError
import com.kinyua_carson.cointrackerpro.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError>{
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException){
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception){
        return Result.Error(NetworkError.UN_KNOWN)
    }

    return responseToResult(response)

}