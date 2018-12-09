package com.example.quarter.ui;

import com.example.quarter.domain.Note;
import com.example.quarter.domain.NotesRepository;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class MainPresenter {

    private MainView view;
    private final NotesRepository notesRepository;

    @Inject
    public MainPresenter(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void loadNotes() {
        notesRepository
                .getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Note>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showLoading();
                    }

                    @Override
                    public void onSuccess(List<Note> notes) {
                        view.hideLoading();
                        view.renderNoteList(notes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }

    public void addNote() {
        Note note = new Note("Title", "Description");
        notesRepository
                .addNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        view.showLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.hideLoading();
                        loadNotes();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoading();
                        view.showError(e.getMessage());
                    }
                });
    }
}
