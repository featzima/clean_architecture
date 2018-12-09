package com.example.quarter.ui

import android.app.ProgressDialog
import android.os.Bundle
import com.example.quarter.domain.Note
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var adapter: NotesAdapter
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { presenter.addNote() }
        recyclerView.adapter = adapter
        presenter.setView(this)
        presenter.loadNotes()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun showError(message: String) {
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun renderNoteList(notes: List<Note>) {
        adapter.notes = notes
    }

    override fun showLoading() {
        progressDialog = ProgressDialog(this).apply {
            setProgressStyle(ProgressDialog.STYLE_SPINNER)
            show()
        }
    }
}
