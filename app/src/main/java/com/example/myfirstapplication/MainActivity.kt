package com.example.myfirstapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapplication.core.ConnectionViewModel
import com.example.myfirstapplication.core.NoInternet
import com.example.myfirstapplication.core.ScreenPreview
import com.example.myfirstapplication.navigation.RootNavigationGraph
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

@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        HandleInternetConnection()
        RootNavigationGraph(navController = rememberNavController())
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