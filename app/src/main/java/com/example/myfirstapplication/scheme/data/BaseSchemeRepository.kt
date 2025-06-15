package com.example.myfirstapplication.scheme.data

import com.example.myfirstapplication.cloudservice.MyScheme
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import javax.inject.Inject

class BaseSchemeRepository @Inject constructor(
    private val myScheme: MyScheme
) : SchemeRepository {
    override suspend fun getUserSchemes(): List<Pair<String, UserDrugScheme>> {
        return myScheme.getAllSchemes()
    }

    override suspend fun addUserScheme(scheme: UserDrugScheme): String {
        return myScheme.uploadScheme(scheme)
    }

    override suspend fun updateUserScheme(
        schemeId: String,
        scheme: UserDrugScheme
    ) {
        myScheme.updateScheme(schemeId, scheme)
    }

    override suspend fun updatePartialScheme(
        schemeId: String,
        updates: Map<String, Any>
    ) {
        myScheme.updatePartialFields(schemeId, updates)
    }
}