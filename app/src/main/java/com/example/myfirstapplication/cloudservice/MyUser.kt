package com.example.myfirstapplication.cloudservice

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import javax.inject.Inject

interface MyUser : UserNotLoggedIn {

    fun checkDataInvalid(): Boolean

    fun signOut()

    fun id(): String

    fun profile(): Pair<String, String>

    fun avatarUrl(): String

    fun userProfileCloud(): Pair<String, String>

    class Base @Inject constructor() : MyUser {

        override fun checkDataInvalid(): Boolean {
            val notValid = userNotLoggedIn()
            return notValid
        }

        override fun signOut() {
            Firebase.auth.signOut()
        }

        override fun id() = if (checkDataInvalid()) "" else user()!!.uid

        override fun profile() = if (checkDataInvalid())
            Pair("", "")
        else {
            val user = user()!!
            Pair(user.email!!, user.displayName ?: "")
        }

        override fun avatarUrl(): String {
            return FirebaseAuth.getInstance().currentUser?.photoUrl?.toString() ?: ""
        }

        override fun userProfileCloud(): Pair<String, String> {
            val user = user() ?: throw IllegalStateException("user null")
            val email = user.email
            if (email.isNullOrEmpty())
                throw IllegalStateException("problem occurred while getting email")
            val displayName = user.displayName ?: email
            return Pair(email, displayName)
        }

        override fun userNotLoggedIn() = user() == null

        private fun user(): FirebaseUser? = Firebase.auth.currentUser
    }
}