package com.yusufcansenturk.firebasewithmvvm.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.data.repository.NoteRepository
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel  @Inject constructor (
    val repository: NoteRepository
) : ViewModel() {


    private val _notes = MutableLiveData<UiState<List<Note>>>()
    val note: LiveData<UiState<List<Note>>>
            get() = _notes

    fun getNotes() {
        _notes.value = UiState.Loading
        _notes.value = repository.getNotes()
    }


}