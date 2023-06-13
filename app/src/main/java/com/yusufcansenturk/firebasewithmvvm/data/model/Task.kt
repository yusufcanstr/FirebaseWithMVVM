package com.yusufcansenturk.firebasewithmvvm.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id: String = "",
    var user_id: String = "",
    val description: String = "",
    val date: String = "",
) : Parcelable
