package com.example.myfirstapplication.profile.data

import com.example.myfirstapplication.cloudservice.MyUser
import com.example.myfirstapplication.profile.domain.ProfileRepository
import com.example.myfirstapplication.profile.domain.UserProfile
import javax.inject.Inject

class BaseProfileRepository @Inject constructor(
    private val myUser: MyUser
) : ProfileRepository {

    override fun init() {
        val myUserId = myUser.id()
        // возможна предзагрузка данных в будущем
    }

    override fun userProfile(): UserProfile {
        val profile = myUser.profile()
        return UserProfile(
            name = profile.second,
            email = profile.first,
            avatarUrl = myUser.avatarUrl(),
        )
    }


    override fun logout() {
        myUser.signOut()
    }
}