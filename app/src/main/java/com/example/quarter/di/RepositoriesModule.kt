package com.example.quarter.di

import com.example.quarter.data.NotesRepositoryImpl
import com.example.quarter.domain.NotesRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoriesModule {

    @Binds
    fun notesRepository(repository: NotesRepositoryImpl): NotesRepository
}
