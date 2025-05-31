package com.example.myfirstapplication.cloudservice

import com.example.myfirstapplication.profile.domain.Drug
import javax.inject.Inject

interface MyDrug {
    suspend fun drugById(drugId: String): Drug
    suspend fun drugByName(name: String): Drug
    suspend fun allDrugs(): List<Pair<String, Drug>>

    class Base @Inject constructor(
        private val service: Service
    ) : MyDrug {

        override suspend fun drugById(drugId: String): Drug {
            val drugs = service.getByQuery(
                path = "drugs",
                queryKey = "id",
                queryValue = drugId,
                clasz = Drug::class.java
            )
            if (drugs.isEmpty()) throw IllegalStateException("No drug found with id = $drugId")
            return drugs.first().second
        }

        override suspend fun drugByName(name: String): Drug {
            val drugs = service.getByQuery(
                path = "drugs",
                queryKey = "name",
                queryValue = name,
                clasz = Drug::class.java
            )
            if (drugs.isEmpty()) throw IllegalStateException("No drug found with name = $name")
            return drugs.first().second
        }

        override suspend fun allDrugs(): List<Pair<String, Drug>> {
            return service.get("drugs", Drug::class.java)
        }
    }
}
