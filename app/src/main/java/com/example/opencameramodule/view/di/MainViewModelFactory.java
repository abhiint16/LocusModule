package com.example.opencameramodule.view.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.opencameramodule.datamanager.MDataManager;

import javax.inject.Inject;

public class MainViewModelFactory<V> implements ViewModelProvider.Factory {
    private V viewModel;

    public MainViewModelFactory(V viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(viewModel.getClass())) {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}