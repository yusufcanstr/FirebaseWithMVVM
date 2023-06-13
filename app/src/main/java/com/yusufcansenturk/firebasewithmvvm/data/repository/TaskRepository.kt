package com.yusufcansenturk.firebasewithmvvm.data.repository

import com.yusufcansenturk.firebasewithmvvm.data.model.Note
import com.yusufcansenturk.firebasewithmvvm.data.model.Task
import com.yusufcansenturk.firebasewithmvvm.data.model.User
import com.yusufcansenturk.firebasewithmvvm.util.UiState

interface TaskRepository {
    fun addTask(task: Task, result: (UiState<Pair<Task, String>>) -> Unit)
    fun updateTask(task: Task, result: (UiState<Pair<Task,String>>) -> Unit)
    fun deleteTask(task: Task, result: (UiState<Pair<Task,String>>) -> Unit)
    fun getTasks(user: User?, result: (UiState<List<Task>>) -> Unit)
}