package com.example.myfirstapplication.di

import com.example.myfirstapplication.profile.data.BaseProfileRepository
import com.example.myfirstapplication.profile.domain.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    abstract fun bindsProfileRepository(
        myProfileRepository: BaseProfileRepository
    ): ProfileRepository
}