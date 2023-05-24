package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.yusufcansenturk.firebasewithmvvm.data.model.Note

class NoteRepositoryImp : NoteRepository {

    override fun getNotes(): List<Note> {
        //We will get data from firebase
        return arrayListOf()
    }




}