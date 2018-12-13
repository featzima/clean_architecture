package com.example.quarter.di;

import com.example.quarter.ui.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface DaggerAndroidModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();

}
