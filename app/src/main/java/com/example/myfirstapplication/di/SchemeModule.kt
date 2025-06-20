package com.example.myfirstapplication.di

import android.app.Application
import android.content.Context
import com.example.myfirstapplication.cloudservice.MyScheme
import com.example.myfirstapplication.scheme.data.BaseSchemeRepository
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
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

@Module
@InstallIn(SingletonComponent::class)
object ContextModule {
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}