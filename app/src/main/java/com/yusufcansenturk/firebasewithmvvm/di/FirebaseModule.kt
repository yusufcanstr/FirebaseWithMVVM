package com.yusufcansenturk.firebasewithmvvm.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance() : FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }



}