package com.kinyua_carson.cointrackerpro.core.domain.util


typealias DomainError = Error

sealed interface Result<out T, out E: Error> {
    data class Success<D>(val data: D) : Result<D, Nothing>
    data class Error<E: DomainError>(val error: E) : Result<Nothing, E>
}

inline fun <T, E: Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> {
    return when(this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(data))
    }
}

inline fun <T, E: Error> Result<T, E>.onSuccess(action: (T) -> Unit): Result<T, E> {
    return when(this){
        is Result.Error -> Result.Error(error)
        is Result.Success-> {
            action(data)
            this
        }

    }
}

inline fun <T, E: Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E>{
    return when(this){
        is Result.Error-> {
            action(error)
            this
        }
        is Result.Success -> this
    }
}