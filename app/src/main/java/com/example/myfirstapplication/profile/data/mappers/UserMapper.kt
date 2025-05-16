package com.example.myfirstapplication.profile.data.mappers

import com.example.myfirstapplication.profile.domain.UserProfile
import javax.inject.Inject

class UserMapper @Inject constructor() {
    fun map(profile: UserProfile): UserProfile {
        return UserProfile(
            name = profile.name,
            email = profile.email,
            avatarUrl = profile.avatarUrl,
        )
    }
}