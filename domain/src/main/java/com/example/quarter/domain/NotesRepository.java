package com.example.quarter.domain;

import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface NotesRepository {

    Completable addNote(Note note);

    Single<List<Note>> getAllNotes();
}
