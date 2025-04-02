package com.example.myfirstapplication.login.data

import com.example.myfirstapplication.cloudservice.Auth
import com.example.myfirstapplication.cloudservice.MyUser
import com.example.myfirstapplication.cloudservice.UserNotLoggedIn
import com.example.myfirstapplication.login.presentation.AuthResultWrapper
import javax.inject.Inject

interface LoginRepository : UserNotLoggedIn {

    suspend fun handleResult(authResult: AuthResultWrapper): LoginResult

    class Base @Inject constructor(
        private val auth: Auth,
        private val myUser: MyUser,
        private val loginCloudDataSource: LoginCloudDataSource
    ) : LoginRepository {

        override fun userNotLoggedIn() = myUser.userNotLoggedIn()

        override suspend fun handleResult(authResult: AuthResultWrapper) =
            if (authResult.isSuccessful()) {
                try {
                    val idToken = authResult.idToken()

                    if (idToken.isEmpty()) {
                        LoginResult.Failed("something went wrong")
                    } else {
                        val (successful, errorMessage) = auth.auth(idToken)
                        if (successful) {
                            loginCloudDataSource.login()
                            LoginResult.Success
                        } else
                            LoginResult.Failed(errorMessage)
                    }
                } catch (e: Exception) {
                    LoginResult.Failed(e.message ?: "something went wrong")
                }
            } else
                LoginResult.Failed("not successful to login")
    }
}