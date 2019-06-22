package com.example.opencameramodule.di;

import com.example.opencameramodule.view.MainActivity;
import com.example.opencameramodule.view.di.module.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Developed by Dharma Sai Seerapu on 18th Dec 2018
 * <p>
 * Builder class for all the activities
 * Declare all activities{@link android.app.Activity} here to use Dagger{@link dagger.android.DaggerActivity}
 */
@Module
public abstract class ActivityBuilderModule {

    //Note: Define All your activities and their corresponding Modules

    @SuppressWarnings("unused")
    @ContributesAndroidInjector(modules = {
            MainModule.class,
    })
    abstract MainActivity mainActivity();

}
