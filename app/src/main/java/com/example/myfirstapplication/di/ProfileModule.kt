package com.example.myfirstapplication.di

import com.example.myfirstapplication.cloudservice.MyDrug
import com.example.myfirstapplication.profile.data.BaseDrugRepository
import com.example.myfirstapplication.profile.data.BaseProfileRepository
import com.example.myfirstapplication.profile.domain.DrugRepository
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

    @Binds
    abstract fun bindsDrugRepository(
        myDrugRepository: BaseDrugRepository
    ): DrugRepository

    @Binds
    abstract fun bindMyDrug(
        myDrug: MyDrug.Base
    ): MyDrug
}