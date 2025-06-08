package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinyua_carson.cointrackerpro.core.domain.util.onError
import com.kinyua_carson.cointrackerpro.core.domain.util.onSuccess
import com.kinyua_carson.cointrackerpro.crypto.data.CoinListRepositoryImpl
import com.kinyua_carson.cointrackerpro.crypto.domain.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinListRepository: CoinListRepositoryImpl
) : ViewModel(){

    private val _state = MutableStateFlow(CoinsListState())
    val state = _state.asStateFlow()

    init {
        loadCoins()
    }

    fun onAction(action: CoinListAction){
        when (action){
            is CoinListAction.OnCoinClick -> {
                selectCoin(coinUI = action.coinUI)
            }
        }
    }

    private fun selectCoin(
        coinUI: CoinUI
    ){
        viewModelScope.launch {
            _state.update { it.copy( selectedCoinUI = coinUI ) }
            coinListRepository
                .getCoinHistory(
                    id = coinUI.id,
                    start = ZonedDateTime.now(),
                    end = ZonedDateTime.now().minusDays(7)
                )
                .onSuccess { history ->
                    println(history)
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun loadCoins(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinListRepository
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            coins = coins.map { it.toCoinUi() },
                            isLoading = false
                        )
                    }
                }
                .onError {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
        }
    }

}