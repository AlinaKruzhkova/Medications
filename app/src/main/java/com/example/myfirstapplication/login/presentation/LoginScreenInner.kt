package com.example.myfirstapplication.login.presentation

import android.app.Activity
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LoginScreenInner(navigate: () -> Unit) {
    val viewModel = hiltViewModel<LoginViewModel>()

    val activity = LocalActivityResultRegistryOwner.current as Activity
    val authResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.handleResult(AuthResultWrapper.Base(it))
        }

    val currentAuthResult by rememberUpdatedState(authResult)

    val state by viewModel.viewState.collectAsStateWithLifecycle()
    state.Update(viewModel, navigate)

    LaunchedEffect(state) {
        state.handle(currentAuthResult, activity)
    }
}