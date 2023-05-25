package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.yusufcansenturk.firebasewithmvvm.data.model.Note

interface NoteRepository {

    fun getNotes(): List<Note>

}