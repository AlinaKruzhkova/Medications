package com.example.myfirstapplication.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.profile.domain.Drug
import com.example.myfirstapplication.profile.domain.DrugRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrugViewModel @Inject constructor(
    private val drugRepository: DrugRepository
) : ViewModel() {

    private val _allDrugs = mutableListOf<Pair<String, Drug>>() // исходный список
    private val _drugs = MutableStateFlow<List<Pair<String, Drug>>>(emptyList())
    val drugs: StateFlow<List<Pair<String, Drug>>> = _drugs

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _forms = MutableStateFlow<List<String>>(emptyList())
    val forms: StateFlow<List<String>> = _forms


    init {
        viewModelScope.launch {
            loadAllDrugs()
            loadAllForms()
        }

        viewModelScope.launch {
            _searchQuery.collect { query ->
                filterDrugs(query)
            }
        }
    }

    private suspend fun loadAllDrugs() {
        val list = drugRepository.getAllDrugs()
        _allDrugs.clear()
        _allDrugs.addAll(list)
        _drugs.value = list
    }

    private suspend fun loadAllForms() {
        val list = drugRepository.getAllForms()

        val normalized = list
            .map { it.trim().lowercase() }
            .distinct()
            .sorted()

        _forms.value = normalized
    }


    private fun filterDrugs(query: String) {
        if (query.isBlank()) {
            _drugs.value = _allDrugs
        } else {
            val filtered = _allDrugs.filter {
                it.second.name.contains(query, ignoreCase = true)
            }
            _drugs.value = filtered
        }
    }

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }
}