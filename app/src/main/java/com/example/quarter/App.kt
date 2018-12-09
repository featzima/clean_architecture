package com.example.quarter

import com.example.quarter.di.AppComponent
import com.example.quarter.di.DaggerAppComponent
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .context(this)
            .build()
    }

    override fun applicationInjector() = appComponent

}