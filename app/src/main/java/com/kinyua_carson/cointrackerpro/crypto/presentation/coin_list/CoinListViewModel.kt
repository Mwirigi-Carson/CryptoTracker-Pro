package com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinyua_carson.cointrackerpro.core.domain.util.onError
import com.kinyua_carson.cointrackerpro.core.domain.util.onSuccess
import com.kinyua_carson.cointrackerpro.crypto.data.CoinListRepositoryImpl
import com.kinyua_carson.cointrackerpro.crypto.domain.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinListRepository: CoinListRepositoryImpl
) : ViewModel(){

    private val _state = MutableStateFlow(CoinsListState())
    val state = _state.asStateFlow()

    private val _filteredCoins = MutableStateFlow(CoinsListState().coins)
    val filteredCoins = _filteredCoins.asStateFlow()

    private var currentSearchQuery = ""

    private val _events = Channel<CoinListEvents>()
    val events = _events.receiveAsFlow()

    init {
        loadCoins()
        viewModelScope.launch {
            state.collect { newState ->
                if (currentSearchQuery.isEmpty()) {
                    _filteredCoins.value = newState.coins
                }
            }
        }
    }

    fun onAction(action: CoinListAction){
        when (action){
            is CoinListAction.OnCoinClick -> {
                selectCoin(coinUI = action.coinUI)
            }
            is CoinListAction.OnSearchQuery -> {
                searchCoin(action.query)
            }
        }
    }

    fun searchCoin(query: String) {
        currentSearchQuery = query
        val filtered = if (query.isBlank()) {
            _state.value.coins // Show all coins when search is empty
        } else {
            _state.value.coins.filter { coin ->
                coin.name.contains(query, ignoreCase = true) ||
                        coin.symbol.contains(query, ignoreCase = true) ||
                        coin.id.contains(query, ignoreCase = true)
            }
        }
        _filteredCoins.value = filtered
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
                .onError { error ->
                    _events.send(CoinListEvents.Error(error))
                }
        }
    }

}