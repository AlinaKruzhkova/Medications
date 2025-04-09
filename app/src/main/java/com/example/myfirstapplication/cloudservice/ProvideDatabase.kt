package com.example.myfirstapplication.cloudservice

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface ProvideDatabase {

    fun database(): DatabaseReference


    class Base @Inject constructor(@ApplicationContext context: Context) : ProvideDatabase {

        init {
            FirebaseApp.initializeApp(context)
            Firebase.database(DATABASE_URL).setPersistenceEnabled(false)
        }

        private val database by lazy { Firebase.database(DATABASE_URL).reference.root }

        override fun database(): DatabaseReference {
            return database
        }

        companion object {
            private const val DATABASE_URL =
                "https://medications-ccfe8-default-rtdb.europe-west1.firebasedatabase.app"
        }
    }
}