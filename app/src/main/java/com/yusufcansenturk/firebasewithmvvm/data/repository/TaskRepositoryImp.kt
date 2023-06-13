package com.yusufcansenturk.firebasewithmvvm.data.repository


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.yusufcansenturk.firebasewithmvvm.data.model.Task
import com.yusufcansenturk.firebasewithmvvm.util.FireDatabase
import com.yusufcansenturk.firebasewithmvvm.util.UiState
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yusufcansenturk.firebasewithmvvm.data.model.User

class TaskRepositoryImp(
    private val database: FirebaseDatabase
) : TaskRepository {

    override fun addTask(task: Task, result: (UiState<Pair<Task,String>>) -> Unit) {
        val reference = database.reference.child(FireDatabase.TASK).push()
        val uniqueKey = reference.key ?: "invalid"
        task.id = uniqueKey
        reference
            .setValue(task)
            .addOnSuccessListener {
                result.invoke(
                    UiState.Success(Pair(task,"Task has been created successfully"))
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

    override fun getTasks(user: User?, result: (UiState<List<Task>>) -> Unit) {
        val reference =
            database.reference.child(FireDatabase.TASK).orderByChild("user_id").equalTo(user?.id)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tasks = arrayListOf<Task?>()
                for (item in dataSnapshot.children) {
                    val task = item.getValue(Task::class.java)
                    tasks.add(task)
                }
                result.invoke(UiState.Success(tasks.filterNotNull()))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                result.invoke(UiState.Failure(databaseError.message))
            }
        })
    }
}