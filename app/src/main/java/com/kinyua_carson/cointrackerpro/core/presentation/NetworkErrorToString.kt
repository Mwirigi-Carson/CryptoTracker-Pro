package com.kinyua_carson.cointrackerpro.core.presentation

import android.content.Context
import com.kinyua_carson.cointrackerpro.R
import com.kinyua_carson.cointrackerpro.core.domain.util.NetworkError

fun NetworkError.toString(context: Context) : String{
    val resId = when(this){
        NetworkError.BAD_REQUEST -> R.string.bad_request
        NetworkError.FORBIDDEN -> R.string.forbidden
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.INCORRECT_API_KEY -> R.string.incorrect_api_key
        NetworkError.UN_KNOWN -> R.string.unknown
        NetworkError.SERIALIZATION -> R.string.serialization
        NetworkError.NO_INTERNET -> R.string.no_internet
    }
    return context.getString(resId)
}