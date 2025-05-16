package com.example.myfirstapplication.login.presentation.state

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myfirstapplication.R
import com.example.myfirstapplication.core.ManageResource
import com.example.myfirstapplication.login.presentation.LoginButtonUi
import com.example.myfirstapplication.login.presentation.LoginScreenUi
import com.example.myfirstapplication.login.presentation.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope

interface LoginUiState {

    fun handle(launcher: ActivityResultLauncher<Intent>, activity: Activity) = Unit

    @Composable
    fun Update(viewModel: LoginViewModel, navigate: () -> Unit) = Unit

    object Initial : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            LoginScreenUi(
                navigate = {
                    viewModel.login()
                }
            )
        }
    }


    data class Error(private val message: String) : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            Column {
                Text(text = message)
                LoginButtonUi(Modifier.Companion) {
                    viewModel.login()
                }
            }
        }
    }

    data class Auth(private val manageResource: ManageResource) : LoginUiState {

        private val options by lazy {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(manageResource.string(R.string.your_web_client_id))
                .requestEmail()
                .requestProfile()
                .requestScopes(Scope(Scopes.DRIVE_FILE))
                .build()
        }

        override fun handle(launcher: ActivityResultLauncher<Intent>, activity: Activity) {
            launcher.launch(GoogleSignIn.getClient(activity, options).signInIntent)
        }

        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            Box(
                contentAlignment = Alignment.Companion.Center,
                modifier = Modifier.Companion.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }

    data object Success : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            navigate.invoke()
        }
    }
}