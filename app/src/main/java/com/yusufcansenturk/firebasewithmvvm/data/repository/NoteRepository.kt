package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.util.UiState

interface NoteRepository {

    fun getNotes(): UiState<List<Note>>

}