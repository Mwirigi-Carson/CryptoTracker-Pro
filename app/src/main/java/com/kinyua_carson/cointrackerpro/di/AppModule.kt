package com.kinyua_carson.cointrackerpro.di

import com.kinyua_carson.cointrackerpro.core.data.networking.HttpClientFactory
import com.kinyua_carson.cointrackerpro.crypto.data.CoinListRepositoryImpl
import com.kinyua_carson.cointrackerpro.crypto.domain.CoinListRepository
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module{
    single { HttpClientFactory.create(CIO.create()) }
    viewModelOf(::CoinListViewModel)
    single { CoinListRepositoryImpl(get()) }.bind<CoinListRepository>()
}