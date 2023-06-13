package com.yusufcansenturk.firebasewithmvvm.ui.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusufcansenturk.firebasewithmvvm.data.model.Task
import com.yusufcansenturk.firebasewithmvvm.data.model.User
import com.yusufcansenturk.firebasewithmvvm.data.repository.TaskRepository
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    val repository: TaskRepository
): ViewModel() {

    private val _addTask = MutableLiveData<UiState<Pair<Task, String>>>()
    val addTask: LiveData<UiState<Pair<Task, String>>>
        get() = _addTask

    private val _tasks = MutableLiveData<UiState<List<Task>>>()
    val tasks: LiveData<UiState<List<Task>>>
        get() = _tasks

    fun addTask(task: Task){
        _addTask.value = UiState.Loading
        repository.addTask(task) { _addTask.value = it }
    }

    fun getTasks(user: User?) {
        _tasks.value = UiState.Loading
        repository.getTasks(user) { _tasks.value = it }
    }

}