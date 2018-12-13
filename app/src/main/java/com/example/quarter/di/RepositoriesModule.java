package com.example.quarter.di;

import com.example.quarter.data.NotesRepositoryImpl;
import com.example.quarter.domain.NotesRepository;
import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoriesModule {

    @Binds
    NotesRepository notesRepository(NotesRepositoryImpl repository);

}
