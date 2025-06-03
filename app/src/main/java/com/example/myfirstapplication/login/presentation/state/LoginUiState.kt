package com.example.myfirstapplication.login.presentation.state

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myfirstapplication.R
import com.example.myfirstapplication.core.ManageResource
import com.example.myfirstapplication.login.presentation.LoginScreenUi
import com.example.myfirstapplication.login.presentation.LoginViewModel
import com.example.myfirstapplication.ui.theme.Burgundy
import com.example.myfirstapplication.ui.theme.Pink
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Pink)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Ошибка",
                            tint = Burgundy,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Что-то пошло не так",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Burgundy
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = { viewModel.login() },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Burgundy)
                        ) {
                            Text("Попробовать снова", color = Color.White)
                        }
                    }
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