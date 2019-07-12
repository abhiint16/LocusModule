package com.example.opencameramodule.view.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.example.opencameramodule.datamanager.MDataManager;
import com.example.opencameramodule.di.ViewModelProviderFactory;
import com.example.opencameramodule.view.di.MainViewModelFactory;
import com.example.opencameramodule.view.viewmodel.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainActivityViewModel providesRegistrationViewModelFactory(MDataManager mDataManager) {
        return new MainActivityViewModel(mDataManager);
    }

    @Provides
    public ViewModelProvider.Factory testViewModelProvider(MainActivityViewModel scheduleTestsFragmentViewModel) {
        return new MainViewModelFactory<>(scheduleTestsFragmentViewModel);
    }

}
