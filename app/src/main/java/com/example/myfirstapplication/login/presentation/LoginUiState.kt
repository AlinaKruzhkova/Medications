package com.example.myfirstapplication.login.presentation

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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import ru.easycode.blogpostsintensive.R
import ru.easycode.blogpostsintensive.core.ManageResource

interface LoginUiState {

    fun handle(launcher: ActivityResultLauncher<Intent>, activity: Activity) = Unit

    @Composable
    fun Update(viewModel: LoginViewModel, navigate: () -> Unit) = Unit

    object Initial : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            LoginButtonUi(Modifier) {
                viewModel.login()
            }
        }
    }

    data class Error(private val message: String) : LoginUiState {
        @Composable
        override fun Update(viewModel: LoginViewModel, navigate: () -> Unit) {
            Column {
                Text(text = message)
                LoginButtonUi(Modifier) {
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
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
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