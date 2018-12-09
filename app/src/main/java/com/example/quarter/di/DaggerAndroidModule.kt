package com.example.quarter.di

import com.example.quarter.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface DaggerAndroidModule {

    @ContributesAndroidInjector
    fun mainActivity(): MainActivity

}