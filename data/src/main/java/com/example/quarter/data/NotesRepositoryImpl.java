package com.example.quarter.data;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.quarter.domain.Note;
import com.example.quarter.domain.NotesRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class NotesRepositoryImpl implements NotesRepository {

    private final static String KEY_PREFERENCES = "KEY_PREFERENCES";
    private final static String KEY_NOTES = "KEY_NOTES";
    private final Context context;
    private final NoteMapper mapper;

    @Inject
    public NotesRepositoryImpl(Context context, NoteMapper mapper) {
        this.context = context;
        this.mapper = mapper;
    }

    @Override
    public Completable addNote(final Note note) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                SharedPreferences sharedPreferences = context.getSharedPreferences("KEY_PREFERENCES", 0);
                String json = sharedPreferences.getString(KEY_NOTES, "[]");

                Gson gson = new Gson();
                List<NoteEntity> noteEntities = gson.fromJson(json, new TypeToken<List<NoteEntity>>() {
                }.getType());

                List<NoteEntity> newNoteEntities = new ArrayList<>(noteEntities);
                newNoteEntities.add(mapper.fromDomain(note));

                String newJson = gson.toJson(newNoteEntities);
                sharedPreferences.edit()
                        .putString(KEY_NOTES, newJson)
                        .commit();
            }
        });
    }

    @Override
    public Single<List<Note>> getAllNotes() {
        return Single
                .fromCallable(new Callable<List<Note>>() {
                    @Override
                    public List<Note> call() throws Exception {
                        SharedPreferences sharedPreferences = context.getSharedPreferences("KEY_PREFERENCES", 0);
                        String json = sharedPreferences.getString(KEY_NOTES, "[]");

                        Gson gson = new Gson();
                        List<NoteEntity> noteEntities = gson.fromJson(json, new TypeToken<List<NoteEntity>>() {
                        }.getType());

                        List<Note> notes = new ArrayList<>();
                        for (NoteEntity entity : noteEntities) {
                            notes.add(mapper.toDomain(entity));
                        }

                        return notes;
                    }
                });
    }
}
