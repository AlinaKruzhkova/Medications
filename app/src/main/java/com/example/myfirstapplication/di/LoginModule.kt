package com.example.myfirstapplication.di

import com.example.myfirstapplication.cloudservice.Auth
import com.example.myfirstapplication.cloudservice.MyUser
import com.example.myfirstapplication.cloudservice.ProvideDatabase
import com.example.myfirstapplication.cloudservice.Service
import com.example.myfirstapplication.core.ManageResource
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.login.data.LoginCloudDataSource
import com.example.myfirstapplication.login.data.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule() {

    @Binds
    abstract fun bindsAuth(
        auth: Auth.Base
    ): Auth

    @Binds
    abstract fun bindsMyUser(
        myUser: MyUser.Base
    ): MyUser

    @Binds
    abstract fun bindsLoginCloudDataSource(
        loginCloudDataSource: LoginCloudDataSource.Base
    ): LoginCloudDataSource

    @Binds
    abstract fun bindsManageResource(
        manageResource: ManageResource.Base
    ): ManageResource

    @Binds
    abstract fun bindsRunAsync(
        runAsync: RunAsync.Base
    ): RunAsync

    @Binds
    abstract fun bindsLoginRepository(
        loginRepository: LoginRepository.Base
    ): LoginRepository

    @Binds
    abstract fun bindsService(
        service: Service.Base
    ): Service

    @Singleton
    @Binds
    abstract fun bindsProvideDatabase(
        provide: ProvideDatabase.Base
    ): ProvideDatabase
}