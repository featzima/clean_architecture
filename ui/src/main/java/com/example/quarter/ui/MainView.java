package com.example.quarter.ui;

import com.example.quarter.domain.Note;

import java.util.List;

public interface MainView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    void renderNoteList(List<Note> notes);
}
