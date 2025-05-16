package com.example.myfirstapplication.profile.domain

interface ProfileRepository {
    fun init()
    fun userProfile(): UserProfile
    fun logout()
}
