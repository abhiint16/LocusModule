package com.example.opencameramodule.di;

import android.app.Application;

import com.example.opencameramodule.MApp;
import com.example.opencameramodule.datamanager.MDataManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MApp mApp);

    MDataManager getDataManager();
}
