package com.example.myfirstapplication.profile.data

import com.example.myfirstapplication.cloudservice.MyDrug
import com.example.myfirstapplication.profile.domain.Drug
import com.example.myfirstapplication.profile.domain.DrugRepository
import javax.inject.Inject

class BaseDrugRepository @Inject constructor(
    private val myDrug: MyDrug
) : DrugRepository {

    override suspend fun getDrugByName(name: String): Drug? {
        return try {
            myDrug.drugByName(name)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getAllDrugs(): List<Pair<String, Drug>> {
        return myDrug.allDrugs()
    }

    override suspend fun getAllForms(): List<String> {
        return try {
            val allDrugs = myDrug.allDrugs()
            allDrugs.map { (_, drug) -> drug.form }
        } catch (e: Exception) {
            emptyList()
        }
    }
}


