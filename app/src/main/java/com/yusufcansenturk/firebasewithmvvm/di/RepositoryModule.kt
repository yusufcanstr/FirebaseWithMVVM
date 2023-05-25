package com.yusufcansenturk.firebasewithmvvm.di

import com.google.firebase.firestore.FirebaseFirestore
import com.yusufcansenturk.firebasewithmvvm.data.repository.NoteRepository
import com.yusufcansenturk.firebasewithmvvm.data.repository.NoteRepositoryImp
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        database: FirebaseFirestore
    ) : NoteRepository{
        return NoteRepositoryImp(database)
    }



}