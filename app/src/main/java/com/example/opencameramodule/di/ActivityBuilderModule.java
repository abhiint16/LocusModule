package com.example.opencameramodule.di;

import com.example.opencameramodule.view.MainActivity;
import com.example.opencameramodule.view.di.module.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    //Note: Define All your activities and their corresponding Modules

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = {
            MainModule.class,
    })
    abstract MainActivity mainActivity();

}
