package com.example.myfirstapplication.menu.presentation.viewmodel

import com.example.myfirstapplication.core.BaseViewModel
import com.example.myfirstapplication.core.RunAsync
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val repository: SchemeRepository,
    runAsync: RunAsync
) : BaseViewModel(runAsync) {

    private val _schemesState = MutableStateFlow<List<Pair<String, UserDrugScheme>>>(emptyList())
    val schemesState = _schemesState.asStateFlow()

    init {
        loadAllActiveSchemes()
    }

    private fun loadAllActiveSchemes() {
        runAsync(
            background = {
                val schemes = repository.getAllActiveSchemes()
                return@runAsync schemes
            },
            uiBlock = { schemes ->
                _schemesState.value = schemes
            }
        )
    }
}