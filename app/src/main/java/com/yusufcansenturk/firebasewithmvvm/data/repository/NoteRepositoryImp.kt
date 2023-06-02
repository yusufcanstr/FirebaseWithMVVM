package com.yusufcansenturk.firebasewithmvvm.data.repository

import android.net.Uri
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.storage.StorageReference
import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.data.model.User
import com.yusufcansenturk.firebasewithmvvm.util.FireStoreCollection
import com.yusufcansenturk.firebasewithmvvm.util.FireStoreDocumentField
import com.yusufcansenturk.firebasewithmvvm.util.FireStoreDocumentField.USER_ID
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class NoteRepositoryImp(
    private val database: FirebaseFirestore,
    private val storageReference: StorageReference
) : NoteRepository {

    override fun getNotes(user: User?, result: (UiState<List<Note>>) -> Unit) {
        database.collection(FireStoreCollection.NOTE)
            .whereEqualTo(USER_ID,user?.id)
            .orderBy(FireStoreDocumentField.DATE, Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                val notes = arrayListOf<Note>()
                for (document in it) {
                    val note = document.toObject(Note::class.java)
                    notes.add(note)
                }
                result.invoke(
                    UiState.Success(notes)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun addNote(note: Note, result: (UiState<Pair<Note,String>>) -> Unit) {
        val document = database.collection(FireStoreCollection.NOTE).document()
        note.id = document.id
        document
            .set(note)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(note,"Note has been created successfully"))
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun updateNote(note: Note, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreCollection.NOTE).document(note.id)
        document
            .set(note)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success("Note has been update successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun deleteNote(note: Note, result: (UiState<String>) -> Unit) {
        database.collection(FireStoreCollection.NOTE).document(note.id)
            .delete()
            .addOnSuccessListener {
                result.invoke(UiState.Success("Note successfully deleted!"))
            }
            .addOnFailureListener { e ->
                result.invoke(UiState.Failure(e.message))
            }
    }

    override suspend fun uploadSingleFile(fileUri: Uri, onResult: (UiState<Uri>) -> Unit) {
        try {
            val uri : Uri = withContext(Dispatchers.IO) {
                storageReference
                    .putFile(fileUri)
                    .await()
                    .storage
                    .downloadUrl
                    .await()
            }
            onResult.invoke(UiState.Success(uri))
        }catch (e: FirebaseException) {
            onResult.invoke(UiState.Failure(e.message))
        }catch (e: Exception) {
            onResult.invoke(UiState.Failure(e.message))
        }
    }
}