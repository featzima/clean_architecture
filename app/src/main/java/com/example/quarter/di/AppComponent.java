package com.example.quarter.di;

import android.content.Context;
import com.example.quarter.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

@Component(modules = {
        DaggerAndroidModule.class,
        RepositoriesModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        AndroidInjector<? extends DaggerApplication> build();
    }

}
