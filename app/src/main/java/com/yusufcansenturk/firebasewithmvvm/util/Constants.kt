package com.yusufcansenturk.firebasewithmvvm.util

object FireStoreCollection{
    const val NOTE = "note"
    const val USER = "user"
}

object FireDatabase{
    val TASK = "task"
}

object FireStoreDocumentField {
    const val DATE = "date"
    const val USER_ID = "user_id"
}

object SharedPrefConstants {
    const val LOCAL_SHARED_PREF = "local_shared_pref"
    const val USER_SESSION = "user_session"
}

object FirebaseStorageConstants {
    const val ROOT_DIRECTORY = "app"
    const val NOTE_IMAGES = "note"
}

enum class HomeTabs(val index: Int, val key: String) {
    NOTES(0, "notes"),
    TASKS(1, "tasks"),
}