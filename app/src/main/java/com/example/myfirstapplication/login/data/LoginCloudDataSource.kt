package com.example.myfirstapplication.login.data

import com.example.myfirstapplication.cloudservice.MyUser
import com.example.myfirstapplication.cloudservice.Service
import com.example.myfirstapplication.common.UserProfileCloud
import javax.inject.Inject

interface LoginCloudDataSource {

    suspend fun login()

    class Base @Inject constructor(
        private val myUser: MyUser,
        private val service: Service
    ) : LoginCloudDataSource {

        override suspend fun login() {
            val id = myUser.id()
            val (mail, name) = myUser.userProfileCloud()
            val userProfile = UserProfileCloud(mail, name)
            service.createWithId("users", id, userProfile)
        }
    }
}