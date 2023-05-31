package com.yusufcansenturk.firebasewithmvvm.di

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.yusufcansenturk.firebasewithmvvm.data.repository.AuthRepository
import com.yusufcansenturk.firebasewithmvvm.data.repository.AuthRepositoryImp
import com.yusufcansenturk.firebasewithmvvm.data.repository.NoteRepository
import com.yusufcansenturk.firebasewithmvvm.data.repository.NoteRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        database: FirebaseFirestore
    ): NoteRepository{
        return NoteRepositoryImp(database)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        database: FirebaseFirestore,
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
        gson : Gson
    ): AuthRepository {
        return AuthRepositoryImp(auth,database, appPreferences,gson)
    }
}