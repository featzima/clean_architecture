package com.example.quarter.data;

import com.example.quarter.domain.Note;

import javax.inject.Inject;

public class NoteMapper {

    @Inject
    public NoteMapper() {
    }

    public Note toDomain(NoteEntity entity) {
        return new Note(entity.title, entity.description);
    }

    public NoteEntity fromDomain(Note note) {
        NoteEntity entity = new NoteEntity();
        entity.title = note.getTitle();
        entity.description = note.getDescription();
        return entity;
    }
}
