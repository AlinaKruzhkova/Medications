package com.example.myfirstapplication.scheme.data

import android.content.Context
import com.example.myfirstapplication.cloudservice.MyScheme
import com.example.myfirstapplication.scheme.domain.SchemeRepository
import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import javax.inject.Inject

class BaseSchemeRepository @Inject constructor(
    private val myScheme: MyScheme,
    private val context: Context
) : SchemeRepository {
    private val prefs by lazy {
        context.getSharedPreferences("scheme_prefs", Context.MODE_PRIVATE)
    }

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
        updates: Map<String, Any?>
    ) {
        myScheme.updatePartialFields(schemeId, updates)
    }

    override suspend fun getCurrentSchemeId(): String? {
        return prefs.getString("current_scheme_id", null)
    }

    override suspend fun setCurrentSchemeId(schemeId: String) {
        prefs.edit().putString("current_scheme_id", schemeId).apply()
    }

    override suspend fun clearCurrentSchemeId() {
        prefs.edit().remove("current_scheme_id").apply()
    }

    override suspend fun markSchemeAsDeleted(schemeId: String) {
        myScheme.markSchemeAsDeleted(schemeId)
    }

    override suspend fun permanentlyDeleteScheme(schemeId: String) {
        myScheme.permanentlyDeleteScheme(schemeId)
    }
}