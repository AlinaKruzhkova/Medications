package com.example.myfirstapplication.profile.domain

interface DrugRepository {
    suspend fun getDrugByName(name: String): Drug?
    suspend fun getDrugById(id: String): Drug?
    suspend fun getAllDrugs(): List<Pair<String, Drug>>
    suspend fun getAllForms(): List<String>
}