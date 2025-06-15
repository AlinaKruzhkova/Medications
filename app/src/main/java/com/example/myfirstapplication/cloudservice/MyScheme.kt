package com.example.myfirstapplication.cloudservice

import com.example.myfirstapplication.scheme.domain.model.UserDrugScheme
import javax.inject.Inject

interface MyScheme {
    suspend fun getAllSchemes(): List<Pair<String /*id_scheme*/, UserDrugScheme>>

    suspend fun uploadScheme(scheme: UserDrugScheme): String

    suspend fun deleteScheme(schemeId: String)

    suspend fun updateScheme(schemeId: String, scheme: UserDrugScheme)

    suspend fun updatePartialFields(string: String, map: Map<String, Any>)

    class Base @Inject constructor(
        private val service: Service,
        private val myUser: MyUser
    ) : MyScheme {

        override suspend fun getAllSchemes(): List<Pair<String, UserDrugScheme>> {
            return service.get(
                path = "users/${myUser.id()}/schemes",
                clasz = UserDrugScheme::class.java
            )
        }

        override suspend fun uploadScheme(scheme: UserDrugScheme): String {
            return service.postFirstLevel(
                path = "users/${myUser.id()}/schemes",
                obj = scheme
            )
        }

        override suspend fun deleteScheme(schemeId: String) {
            service.remove(
                path = "users/${myUser.id()}/schemes",
                child = schemeId
            )
        }

        override suspend fun updateScheme(schemeId: String, scheme: UserDrugScheme) {
            service.createWithId(
                path = "users/${myUser.id()}/schemes",
                id = schemeId,
                value = scheme
            )
        }

        override suspend fun updatePartialFields(
            schemeId: String,
            updates: Map<String, Any>
        ) {
            val path = "users/${myUser.id()}/schemes"

            updates.forEach { (field, value) ->
                service.updateField(
                    path = path,
                    child = schemeId,
                    fieldId = field,
                    fieldValue = value
                )
            }
        }
    }
}