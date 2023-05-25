package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import java.util.*

class NoteRepositoryImp(
    val database: FirebaseFirestore
) : NoteRepository {

    override fun getNotes():UiState<List<Note>> {
        //We will get data from firebase
        val data =  listOf<Note>()

        if (data.isNullOrEmpty()) {
            return UiState.Failure("Data is Empty")
        }else {
            return UiState.Success(data)
        }
    }




}