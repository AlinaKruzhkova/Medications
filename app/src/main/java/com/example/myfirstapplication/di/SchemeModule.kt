package com.example.myfirstapplication.di

import com.example.myfirstapplication.cloudservice.MyScheme
import com.example.myfirstapplication.scheme.data.BaseSchemeRepository
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SchemeModule {

    @Binds
    abstract fun bindsSchemeRepository(
        mySchemeRepository: BaseSchemeRepository
    ): SchemeRepository

    @Binds
    abstract fun bindMyScheme(
        myScheme: MyScheme.Base
    ): MyScheme
}