package com.example.quarter.di

import android.content.Context
import com.example.quarter.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

@Component(modules = [
    DaggerAndroidModule::class,
    RepositoriesModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}