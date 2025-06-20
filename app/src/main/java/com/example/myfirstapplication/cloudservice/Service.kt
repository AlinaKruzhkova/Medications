package com.example.myfirstapplication.cloudservice

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface Service {

    interface Callback<T : Any> {

        fun provide(obj: List<Pair<String, T>>)

        fun error(message: String)
    }

    fun remove(path: String, child: String)

    fun <T : Any> startListen(
        path: String, clasz: Class<T>,
        callback: Callback<T>,
    )

    fun <T : Any> getByQueryAsync(
        path: String,
        queryKey: String,
        queryValue: String,
        clasz: Class<T>,
        callback: Callback<T>,
    )

    fun <T : Any> getByQueryAsync(
        path: String,
        queryValue: String,
        clasz: Class<T>,
        callback: Callback<T>,
    )

    suspend fun <T : Any> getByQuery(
        path: String,
        queryKey: String,
        queryValue: String,
        clasz: Class<T>,
    ): List<Pair<String, T>>

    suspend fun <T : Any> getByQueryStartWith(
        path: String,
        queryKey: String,
        queryValue: String,
        clasz: Class<T>,
        limit: Int = 100,
    ): List<Pair<String, T>>

    suspend fun <T : Any> getByQueryValue(
        path: String,
        clasz: Class<T>,
    ): List<Pair<String, T>>

    fun updateField(path: String, child: String, fieldId: String, fieldValue: Any?)

    suspend fun updateFields(path: String, child: String, updates: Map<String, Any?>)

    fun postFirstLevelAsync(path: String, obj: Any)

    suspend fun postFirstLevel(path: String, obj: Any): String

    suspend fun createWithId(path: String, id: String, value: Any)

    suspend fun <T : Any> get(
        path: String, clasz: Class<T>,
    ): List<Pair<String, T>>

    class Base @Inject constructor(provideDatabase: ProvideDatabase) : Service {

        private val database = provideDatabase.database()

        override fun remove(path: String, child: String) {
            database.child(path).child(child).removeValue()
        }

        override fun <T : Any> startListen(path: String, clasz: Class<T>, callback: Callback<T>) {
            database.child(path).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children.mapNotNull {
                        Pair(it.key!!, it.getValue(clasz)!!)
                    }
                    callback.provide(data)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback.error(error.message)
                }
            })
        }

        override fun updateField(path: String, child: String, fieldId: String, fieldValue: Any?) {
            database
                .child(path)
                .child(child)
                .child(fieldId)
                .setValue(fieldValue)
        }

        override suspend fun updateFields(
            path: String,
            child: String,
            updates: Map<String, Any?>
        ) {
            val result = database
                .child(path)
                .child(child)
                .updateChildren(updates)
            handleResult(result)
        }

        override fun <T : Any> getByQueryAsync(
            path: String,
            queryKey: String,
            queryValue: String,
            clasz: Class<T>,
            callback: Callback<T>,
        ) {
            database
                .child(path)
                .orderByChild(queryKey)
                .equalTo(queryValue)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val data = snapshot.children.mapNotNull {
                            Pair(it.key!!, it.getValue(clasz)!!)
                        }
                        callback.provide(data)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        callback.error(error.message)
                    }
                })
        }

        override fun <T : Any> getByQueryAsync(
            path: String,
            queryValue: String,
            clasz: Class<T>,
            callback: Callback<T>,
        ) {
            database
                .child(path)
                .orderByKey()
                .equalTo(queryValue)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = snapshot.children.mapNotNull {
                            Pair(
                                it.key!!,
                                it.getValue(clasz)!!
                            )
                        }
                        callback.provide(list)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        callback.error(error.message)
                    }

                })
        }

        override suspend fun <T : Any> getByQuery(
            path: String,
            queryKey: String,
            queryValue: String,
            clasz: Class<T>,
        ): List<Pair<String, T>> {
            val query = database
                .child(path)
                .orderByChild(queryKey)
                .equalTo(queryValue)
            return handleQuery(query, clasz)
        }

        override suspend fun <T : Any> getByQueryValue(
            path: String,
            clasz: Class<T>,
        ): List<Pair<String, T>> {
            val query = database
                .child(path)
                .orderByKey()
            return handleQuery(query, clasz)
        }

        override suspend fun <T : Any> getByQueryStartWith(
            path: String,
            queryKey: String,
            queryValue: String,
            clasz: Class<T>,
            limit: Int,
        ): List<Pair<String, T>> {
            val query = database
                .child(path)
                .orderByChild(queryKey)
                .startAt(queryValue)
                .limitToFirst(limit)
            return handleQuery(query, clasz)
        }

        private suspend fun <T : Any> handleQuery(
            query: Query,
            clasz: Class<T>,
        ) = suspendCoroutine { cont ->
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children.mapNotNull {
                        Pair(
                            it.key!!,
                            it.getValue(clasz)!!
                        )
                    }
                    cont.resume(data)
                }

                override fun onCancelled(error: DatabaseError) =
                    cont.resumeWithException(IllegalStateException(error.message))
            })
        }

        override fun postFirstLevelAsync(path: String, obj: Any) {
            database.child(path).push().setValue(obj)
        }

        override suspend fun postFirstLevel(path: String, obj: Any): String {
            val reference = database.child(path).push()
            val task = reference.setValue(obj)
            handleResult(task)
            return reference.key!!
        }

        override suspend fun createWithId(path: String, id: String, value: Any) {
            val result = database
                .child(path)
                .child(id)
                .setValue(value)
            handleResult(result)
        }

        override suspend fun <T : Any> get(path: String, clasz: Class<T>): List<Pair<String, T>> {
            return handleOneTime(database.child(path), clasz)
        }

        private suspend fun <T : Any> handleOneTime(
            task: DatabaseReference,
            clasz: Class<T>,
        ) = suspendCoroutine { cont ->
            task.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children.mapNotNull {
                        Pair(
                            it.key!!,
                            it.getValue(clasz)!!
                        )
                    }
                    cont.resume(data)
                }

                override fun onCancelled(error: DatabaseError) =
                    cont.resumeWithException(IllegalStateException(error.message))
            })
        }

        private suspend fun handleResult(value: Task<Void>): Unit =
            suspendCoroutine { continuation ->
                value.addOnSuccessListener {
                    continuation.resume(Unit)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
            }
    }
}