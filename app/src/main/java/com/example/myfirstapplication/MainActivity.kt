package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myfirstapplication.core.ScreenPreview
import com.example.myfirstapplication.core.network.ConnectionViewModel
import com.example.myfirstapplication.core.network.NoInternet
import com.example.myfirstapplication.navigation.RootNavigationGraph
import com.example.myfirstapplication.ui.theme.Pink
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink)
    ) {
        HandleInternetConnection()
        RootNavigationGraph(navController = rememberAnimatedNavController())
    }
}

@Composable
fun HandleInternetConnection() {
    val connectionViewModel: ConnectionViewModel = hiltViewModel()
    val isConnected by connectionViewModel.connectedStateFlow.collectAsStateWithLifecycle()

    if (!isConnected) {
        NoInternet()
    }
}


@ScreenPreview
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}