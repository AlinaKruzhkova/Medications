package com.example.myfirstapplication.profile.presentation.viewmodel

import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.profile.data.mappers.UserMapper
import com.example.myfirstapplication.profile.domain.ProfileRepository
import com.example.myfirstapplication.profile.domain.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val userMapper: UserMapper,
    runAsync: RunAsync,
) : BaseViewModel(runAsync) {

    private val _user = MutableStateFlow<UserProfile?>(null)

    init {
        runAsync(
            background = {
                repository.init()
                repository.userProfile()
            },
            uiBlock = { profile ->
                _user.value = profile
            }
        )
    }


    fun logout() = repository.logout()

    fun userInfo(): UserProfile {
        return userMapper.map(repository.userProfile())
    }
}