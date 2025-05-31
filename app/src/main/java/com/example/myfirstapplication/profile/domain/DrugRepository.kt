package com.example.myfirstapplication.profile.domain

interface DrugRepository {
    suspend fun getDrugByName(name: String): Drug?
    suspend fun getAllDrugs(): List<Pair<String, Drug>>
}
