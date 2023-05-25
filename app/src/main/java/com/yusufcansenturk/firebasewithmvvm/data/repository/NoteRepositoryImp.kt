package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import java.util.*

class NoteRepositoryImp(
    val database: FirebaseFirestore
) : NoteRepository {

    override fun getNotes(): List<Note> {
        //We will get data from firebase
        return arrayListOf(
            Note(
                id = "fdasf",
                text = "Note 2",
                date = Date()
            ),
            Note(
                id = "fdasf",
                text = "Note 2",
                date = Date()
            ),
            Note(
                id = "fdasf",
                text = "Note 2",
                date = Date()
            )
        )
    }




}