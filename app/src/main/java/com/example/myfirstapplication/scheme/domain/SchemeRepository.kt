package com.example.myfirstapplication.scheme.domain

import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme

interface SchemeRepository {
    suspend fun getUserSchemes(): List<Pair<String, UserDrugScheme>>
    suspend fun addUserScheme(scheme: UserDrugScheme): String
    suspend fun updateUserScheme(schemeId: String, scheme: UserDrugScheme)
    suspend fun updatePartialScheme(schemeId: String, updates: Map<String, Any?>)
}