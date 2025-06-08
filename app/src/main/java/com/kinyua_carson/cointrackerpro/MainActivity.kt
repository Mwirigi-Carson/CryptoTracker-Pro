package com.kinyua_carson.cointrackerpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.kinyua_carson.cointrackerpro.core.navigation.AppNavigation
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_details.CoinDetailsScreen
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListScreen
import com.kinyua_carson.cointrackerpro.crypto.presentation.coin_list.CoinListViewModel
import com.kinyua_carson.cointrackerpro.ui.theme.CryptoTrackerProTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerProTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val navController = rememberNavController()

                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        state = state,
                        viewModel = viewModel
                    )

                }
            }
        }
    }
}
