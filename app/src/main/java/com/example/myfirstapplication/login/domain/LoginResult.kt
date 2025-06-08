package com.example.myfirstapplication.login.domain

import com.example.myfirstapplication.login.presentation.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow

interface LoginResult {

    fun map(viewState: MutableStateFlow<LoginUiState>)

    object Success : LoginResult {

        override fun map(
            viewState: MutableStateFlow<LoginUiState>,
        ) {
            viewState.value = LoginUiState.Success
        }
    }

    data class Failed(private val message: String) : LoginResult {

        override fun map(
            viewState: MutableStateFlow<LoginUiState>,
        ) {
            viewState.value = LoginUiState.Error(message)
        }
    }
}