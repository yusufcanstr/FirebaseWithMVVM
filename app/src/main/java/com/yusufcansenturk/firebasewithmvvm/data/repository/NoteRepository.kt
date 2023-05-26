package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.util.UiState

interface NoteRepository {

    fun getNotes(result: (UiState<List<Note>>) -> Unit)

    fun addNote(note: Note, result: (UiState<String>) -> Unit)

}