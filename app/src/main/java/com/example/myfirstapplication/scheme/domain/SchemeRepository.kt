package com.example.myfirstapplication.scheme.domain

import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme

interface SchemeRepository {
    suspend fun getUserSchemes(): List<Pair<String, UserDrugScheme>>
    suspend fun addUserScheme(scheme: UserDrugScheme): String
    suspend fun updateUserScheme(schemeId: String, scheme: UserDrugScheme)
    suspend fun updatePartialScheme(schemeId: String, updates: Map<String, Any?>)

    // Добавляем методы для работы с текущим schemeId
    suspend fun getCurrentSchemeId(): String?
    suspend fun setCurrentSchemeId(schemeId: String)
    suspend fun clearCurrentSchemeId()
}